package util.redis;

import io.lettuce.core.RedisChannelWriter;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.StatefulRedisClusterConnectionImpl;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * redis连接
 *
 * @author liukang_lc on 2021/1/25
 */
public class RedisConnection{

    private static Logger logger = LoggerFactory.getLogger(RedisConnection.class);

    /**
     * 单机模式或哨兵模式的client
     */
    private RedisClient redisClient;
    /**
     * 集群模式的client
     */
    private RedisClusterClient redisClusterClient;
    /**
     * 单机或哨兵模式的连接池
     */
    private GenericObjectPool<StatefulRedisConnection> redisPool;

    private OperationsForValue opsForValue=new OperationsForValue(this);
    public OperationsForValue opsForValue(){
        return this.opsForValue;
    }

    /**
     * 集群模式下的连接池
     */
    private StatefulRedisClusterConnection clusterConnection;

    public StatefulRedisClusterConnection getClusterConnection() {
        return clusterConnection;
    }

    /**
     * 是否是集群模式
     */
    private boolean cluster = false;

    public boolean isCluster() {
        return cluster;
    }

    /**
     * redis配置项
     */
    private RedisConfig redisConfig;

    public RedisConnection(RedisConfig redisConfig) {
        if(redisConfig == null){
            throw new IllegalArgumentException("redisConfig can not be null");
        }

        this.redisConfig = redisConfig;
        // 初始化连接
        this.initConnection();
    }

    /**
     * 初始化连接
     */
    private void initConnection(){
        if(this.redisConfig.getSentinel()!=null){
            // 哨兵模式
            this.initSentinelConnection();
        }else if(this.redisConfig.getCluster()!=null){
            // 集群模式
            this.initClusterConnection();
        }else{
            // 单机模式
            this.initStandaloneConnection();
        }
    }

    public StatefulRedisConnection getConnection(){
        StatefulRedisConnection connection = null;
        try {
            connection = this.redisPool.borrowObject();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return connection;
    }

    /**
     * 单机模式初始化连接
     */
    private void initStandaloneConnection(){
        RedisURI redisUri=RedisURI.builder()
                .withHost(redisConfig.getHost())
                .withPort(redisConfig.getPort())
                .withDatabase(redisConfig.getDatabase())
                .withTimeout(Duration.ofSeconds(redisConfig.getTimeout()))
                .withSsl(redisConfig.isSsl()).build();
        if(StringUtils.isNotEmpty(redisConfig.getPassword())){
            redisUri.setPassword(redisConfig.getPassword());
        }
        if(StringUtils.isNotEmpty(redisConfig.getClientName())){
            redisUri.setClientName(redisConfig.getClientName());
        }
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        if(redisConfig.getPool()!=null){
            poolConfig.setMaxIdle(redisConfig.getPool().getMaxIdle());
            poolConfig.setMinIdle(redisConfig.getPool().getMinIdle());
            poolConfig.setMaxTotal(redisConfig.getPool().getMaxActive());
        }
        redisClient = RedisClient.create(redisUri);
        redisPool = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect,poolConfig);
        logger.info("==========redis单机版连接完成===========");
    }

    /**
     * 哨兵模式初始化连接
     */
    private void initSentinelConnection(){
        if(redisConfig.getSentinel().getNodes()==null || redisConfig.getSentinel().getNodes().size()==0){
            throw new IllegalArgumentException("redis sentinel nodes can not be empty");
        }
        String masterName = redisConfig.getSentinel().getMaster();
        String sentinelPassword = redisConfig.getSentinel().getPassword();
        RedisURI.Builder builder = RedisURI.builder();
        redisConfig.getSentinel().getNodes().forEach(node->{
            String[] nodeArray = node.split(":");
            if(nodeArray.length!=2){
                throw new RuntimeException("哨兵模式下，节点信息必须是host:port的形式");
            }
            String host = nodeArray[0];
            int port = Integer.valueOf(nodeArray[1]);
            RedisURI.Builder sentinelBuilder = RedisURI.Builder.redis(host, port);
            if(StringUtils.isNotEmpty(sentinelPassword)){
                sentinelBuilder.withPassword(sentinelPassword);
            }
            builder.withSentinel(sentinelBuilder.build());
        });
        if(StringUtils.isNotEmpty(redisConfig.getPassword())){
            builder.withPassword(redisConfig.getPassword());
        }
        if(StringUtils.isEmpty(masterName)){
            throw new RuntimeException("哨兵模式下，masterName不能为空");
        }
        builder.withSentinelMasterId(masterName);

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        if(redisConfig.getPool()!=null){
            poolConfig.setMaxIdle(redisConfig.getPool().getMaxIdle());
            poolConfig.setMinIdle(redisConfig.getPool().getMinIdle());
            poolConfig.setMaxTotal(redisConfig.getPool().getMaxActive());
        }
        redisClient = RedisClient.create(builder.build());
        redisPool = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect,poolConfig);
        logger.info("==========sentinel模式下redis连接完成===========");
    }

    /**
     * 集群模式初始化话连接
     */
    private void initClusterConnection(){
        this.cluster=true;
        if(redisConfig.getCluster().getNodes()==null || redisConfig.getCluster().getNodes().size()==0){
            throw new IllegalArgumentException("redis cluster nodes can not be empty");
        }
        Set<RedisURI> nodes = new HashSet<>(redisConfig.getCluster().getNodes().size());
        String password = redisConfig.getPassword();
        redisConfig.getCluster().getNodes().forEach(node->{
            String[] nodeArray = node.split(":");
            if(nodeArray.length!=2){
                throw new RuntimeException("集群模式下，节点信息必须是host:port的形式");
            }
            String host = nodeArray[0];
            int port = Integer.valueOf(nodeArray[1]);

            RedisURI redisUri=RedisURI.builder()
                    .withHost(host)
                    .withPort(port)
                    .withDatabase(redisConfig.getDatabase())
                    .withTimeout(Duration.ofSeconds(redisConfig.getTimeout()))
                    .withSsl(redisConfig.isSsl()).build();
            if(StringUtils.isNotEmpty(password)){
                redisUri.setPassword(password);
            }
            if(StringUtils.isNotEmpty(redisConfig.getClientName())){
                redisUri.setClientName(redisConfig.getClientName());
            }
            nodes.add(redisUri);
        });
        DefaultClientResources.Builder resourceBuilder=DefaultClientResources.builder();
        ClientResources resources= resourceBuilder.build();

        redisClusterClient = RedisClusterClient.create(resources,nodes);
        ClusterClientOptions.Builder builder = ClusterClientOptions.builder()
                //是否自动重连
                .autoReconnect(true)
                //连接激活之前是否执行PING命令
                .pingBeforeActivateConnection(true)
                //是否校验集群节点的成员关系
                .validateClusterNodeMembership(true);
        if(redisConfig.getCluster().getMaxRedirects()!=null){
            builder = builder.maxRedirects(redisConfig.getCluster().getMaxRedirects());
        }

        ClusterClientOptions options = builder.build();
        redisClusterClient.setOptions(options);
        clusterConnection = redisClusterClient.connect();
        logger.info("==========cluster模式下redis连接完成===========");
    }

    public boolean delKey(String key){
        RedisFuture<Long> future = null;
        if(this.cluster){
            // 集群模式下
            RedisAdvancedClusterAsyncCommands commands = this.clusterConnection.async();
            future = commands.del(key);
        }else{
            // 单机或哨兵模式下
            StatefulRedisConnection connection = this.getConnection();
            if(connection!=null){
                RedisAsyncCommands<String,String> commands= connection.async();
                future = commands.del(key);
                connection.close();
            }else{
                return false;
            }
        }
        if(future!=null){
            try {
                Long result = future.get();
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
