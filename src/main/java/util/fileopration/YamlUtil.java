package util.fileopration;

import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * yml文件读取工具类
 *
 * @author liukang_lc on 2020/11/30
 */
public class YamlUtil {

    private static Map<String, Map<String,Object>> ymlMap = null;

    private final static String POINT = ".";

    /**
     * 加载配置文件
     * @param ymlFile
     */
    public static void loadYaml(String ymlFile){
        // 根据传入的参数不同选择加载不同的配置文件
        Yaml yaml = new Yaml();
        try(InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream(ymlFile)) {
            ymlMap = yaml.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("未成功加载配置文件"+ymlFile+",请检查配置文件是否正确");
        }
    }

    public static Object getValue(String key){
        if(ymlMap == null){
            return null;
        }
        String[] separatorKeys = null;
        if(key.contains(POINT)){
            separatorKeys = key.split("\\.");
        }else{
            return ymlMap.get(key);
        }
        if(separatorKeys.length==0){
            return null;
        }
        Map<String, Map<String, Object>> finalValue = (Map) ymlMap.get(separatorKeys[0]);
        for (int i = 1; i < separatorKeys.length-1; i++) {
            if(finalValue==null){
                break;
            }
            finalValue = (Map) finalValue.get(separatorKeys[i]);
        }
        if(finalValue == null){
            return null;
        }else{
            return finalValue.get(separatorKeys[separatorKeys.length-1]);
        }
    }
}
