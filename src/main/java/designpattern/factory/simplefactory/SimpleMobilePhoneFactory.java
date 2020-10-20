package designpattern.factory.simplefactory;

import designpattern.factory.factorymethod.HuaWeiMobilePhone;
import designpattern.factory.factorymethod.MiMobilePhone;
import designpattern.factory.factorymethod.MobilePhone;

/**
 * 手机工厂
 *
 * @author liukang_lc on 2020/10/20
 */
public class SimpleMobilePhoneFactory {
    private static MiMobilePhone generateMi(){
        System.out.println("生产小米手机");
        return new MiMobilePhone();
    }

    private static HuaWeiMobilePhone generateHuawei(){
        System.out.println("生产华为手机");
        return new HuaWeiMobilePhone();
    }

    public static String MI="MI";
    public static String HUAWEI= "HuaWei";

    public static MobilePhone generateMobile(String type){
        if(MI.equals(type)){
            return generateMi();
        }else if(HUAWEI.equals(type)){
            return generateHuawei();
        }else{
            return null;
        }
    }

}
