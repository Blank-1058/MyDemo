package util.redis;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * value为string类型时的操作
 *
 * @author liukang_lc on 2021/1/25
 */
public class OperationsForValue {

    private static Logger logger = LoggerFactory.getLogger(OperationsForValue.class);

    private RedisConnection redisConnection;

    public OperationsForValue(RedisConnection redisConnection) {
        this.redisConnection = redisConnection;
    }

    public boolean set(String key,String value){
        RedisFuture<String> future = null;
        if(this.redisConnection.isCluster()){
            // 集群模式下
            RedisAdvancedClusterAsyncCommands commands = this.redisConnection.getClusterConnection().async();
            future = commands.set(key, value);
        }else{
            // 单机或哨兵模式下
            StatefulRedisConnection connection = this.redisConnection.getConnection();
            if(connection!=null){
                RedisAsyncCommands<String,String> commands= connection.async();
                future = commands.set(key, value);
                connection.close();
            }else{
                return false;
            }
        }
        if(future!=null){
            try {
                String result = future.get();
                if("ok".equals(result.toLowerCase())){
                    return true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String get(String key){
        RedisFuture<String> future = null;
        if(this.redisConnection.isCluster()){
            // 集群模式下
            RedisAdvancedClusterAsyncCommands commands = this.redisConnection.getClusterConnection().async();
            future = commands.get(key);
        }else{
            // 单机或哨兵模式下
            StatefulRedisConnection connection = this.redisConnection.getConnection();
            if(connection!=null){
                RedisAsyncCommands<String,String> commands= connection.async();
                future = commands.get(key);
                connection.close();
            }else{
                return null;
            }
        }
        if(future!=null){
            try {
                String result = future.get();
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
