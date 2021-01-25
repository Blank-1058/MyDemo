package util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.fileopration.YamlUtil;

/**
 * redis操作main函数
 *
 * @author liukang_lc on 2021/1/25
 */
public class RedisMain {

    private static Logger logger= LoggerFactory.getLogger(RedisMain.class);

    public static void main(String[] args) {
        // 读取yml文件
        String ymlPath = "conf/redisconf/redis-conf.yml";
        RedisConfig redisConfig = RedisConfig.buildRedisConfigFromYml(ymlPath);
        RedisConnection redisConnection = new RedisConnection(redisConfig);

        String key = "test";
        String value = "test";
        if(redisConnection.opsForValue().set(key,value)){
            logger.info("===存入key:{},value:{}成功===",key,value);
        }else{
            logger.info("===存入key:{},value:{}失败===",key,value);
        }

        String result = redisConnection.opsForValue().get(key);
        logger.info("===获取key:{}对应的value:{}===",key,result);

        if(redisConnection.delKey(key)){
            logger.info("===删除key:{}成功===",key);
        }else{
            logger.info("===删除key:{}失败===",key);
        }
    }
}
