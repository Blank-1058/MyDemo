package util.redis;

import util.fileopration.YamlUtil;

import java.util.List;

/**
 * redis配置类（模仿Spring-redis的配置）
 * （配置项参考redis-conf.yml）
 * @author liukang_lc on 2021/1/22
 */
public class RedisConfig {

    /**
     * 连接的数据库
     */
    private int database = 0;

    /**
     * redis服务地址
     */
    private String host = "localhost";

    /**
     * redis端口号
     */
    private int port = 6379;

    /**
     * redis服务的连接密码
     */
    private String password;

    /**
     * 是否开启ssl
     */
    private boolean ssl = false;

    /**
     * 连接超时时间
     */
    private int timeout = 10;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 哨兵模式配置
     */
    private Sentinel sentinel;

    /**
     * 集群模式配置
     */
    private Cluster cluster;

    /**
     * 连接池配置
     */
    private Pool pool;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Sentinel getSentinel() {
        return sentinel;
    }

    public void setSentinel(Sentinel sentinel) {
        this.sentinel = sentinel;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    /**
     * 哨兵模式配置
     */
    public static class Sentinel{

        /**
         * redis服务的名称
         */
        private String master;

        /**
         * redis节点，格式 host:port
         */
        private List<String> nodes;

        /**
         * 哨兵模式下各个节点通信的密码
         */
        private String password;

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * 集群模式配置
     */
    public static class Cluster {

        /**
         * 集群模式节点，格式：host:port，必须有至少一个节点
         */
        private List<String> nodes;

        /**
         * 集群连接的最大重定向次数
         * 集群模式下，redis存储或者获取value时，经过计算后，会得到key应该存储的节点，
         * 如果这个key不在当前节点，则redis会自动路由到存储这个key的节点，这个过程被称为redirect）
         */
        private Integer maxRedirects;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public Integer getMaxRedirects() {
            return maxRedirects;
        }

        public void setMaxRedirects(Integer maxRedirects) {
            this.maxRedirects = maxRedirects;
        }
    }

    /**
     * redis连接池配置
     */
    public static class Pool{

        /**
         * 连接池内的最大空闲连接数
         */
        private int maxIdle = 8;
        /**
         * 连接池内的最小空闲连接数
         */
        private int minIdle = 0;

        /**
         * 最大活跃连接数
         */
        private int maxActive = 8;

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }
    }

    /**
     * 从yml文件中读取配置
     * @param filePath
     * @return
     */
    public static RedisConfig buildRedisConfigFromYml(String filePath){
        YamlUtil.loadYaml(filePath);
        RedisConfig redisConfig=new RedisConfig();
        Object database = YamlUtil.getValue("redis.database");
        if(database!=null){
            redisConfig.setDatabase(Integer.valueOf(database.toString()));
        }
        Object host = YamlUtil.getValue("redis.host");
        if(host!=null){
            redisConfig.setHost(host.toString());
        }
        Object port = YamlUtil.getValue("redis.port");
        if(port!=null){
            redisConfig.setPort(Integer.valueOf(port.toString()));
        }
        Object password = YamlUtil.getValue("redis.password");
        if(password!=null){
            redisConfig.setPassword(password.toString());
        }
        Object ssl = YamlUtil.getValue("redis.ssl");
        if(ssl!=null){
            redisConfig.setSsl(Boolean.parseBoolean(ssl.toString()));
        }
        Object timeout = YamlUtil.getValue("redis.timeout");
        if(timeout!=null){
            redisConfig.setTimeout(Integer.valueOf(timeout.toString()));
        }
        Object clientName = YamlUtil.getValue("redis.clientName");
        if(clientName!=null){
            redisConfig.setClientName(clientName.toString());
        }
        // 连接池配置
        RedisConfig.Pool pool=new RedisConfig.Pool();
        Object maxIdle = YamlUtil.getValue("redis.pool.maxIdle");
        if(maxIdle!=null){
            pool.setMaxIdle(Integer.valueOf(maxIdle.toString()));
        }
        Object minIdle = YamlUtil.getValue("redis.pool.minIdle");
        if(minIdle!=null){
            pool.setMinIdle(Integer.valueOf(minIdle.toString()));
        }
        Object maxActive = YamlUtil.getValue("redis.pool.maxActive");
        if(maxActive!=null){
            pool.setMaxActive(Integer.valueOf(maxActive.toString()));
        }
        redisConfig.setPool(pool);

        // 集群模式配置
        RedisConfig.Cluster cluster=new RedisConfig.Cluster();
        Object clusterNodes = YamlUtil.getValue("redis.cluster.nodes");
        if(clusterNodes!=null){
            cluster.setNodes((List<String>)clusterNodes);
            Object maxRedirects = YamlUtil.getValue("redis.cluster.maxRedirects");
            if(maxRedirects!=null){
                cluster.setMaxRedirects(Integer.valueOf(maxRedirects.toString()));
            }
            redisConfig.setCluster(cluster);
        }
        // 哨兵模式配置
        RedisConfig.Sentinel sentinel=new RedisConfig.Sentinel();
        Object sentinelNodes = YamlUtil.getValue("redis.sentinel.nodes");
        if(sentinelNodes!=null){
            sentinel.setNodes((List<String>)sentinelNodes);
            Object sentinelMater = YamlUtil.getValue("redis.sentinel.master");
            if(sentinelMater==null){
                throw new RuntimeException("哨兵模式下master配置不能空");
            }
            sentinel.setMaster(sentinelMater.toString());
            Object sentinelPassword = YamlUtil.getValue("redis.sentinel.password");
            if(sentinelPassword!=null){
                sentinel.setPassword(sentinelPassword.toString());
            }
            redisConfig.setSentinel(sentinel);
        }
        return redisConfig;
    }
}
