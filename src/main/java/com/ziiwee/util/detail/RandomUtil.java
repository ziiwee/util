package com.ziiwee.util.detail;

import java.util.Date;
import java.util.Random;

/**
 * Created by ziiwee on 2016/11/8.
 */
public class RandomUtil {

    /**
     * 获取随机数
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        return fillRandom(new Random().nextInt(Integer.parseInt("1"+fillRandom("",length)))+"",length);
    }

    /**
     * 补全随机数位数
     * @param random
     * @return
     */
    public static String fillRandom(String random,int length){
        if(random.length()<length){
            random = '0'+random;
            random = fillRandom(random,length);
        }
        return random;
    }

    /**
     * 获取日期随机ID
     * @return
     */
    public static String getRandomDateID(){
        return DateUtil.format(new Date(),"yyyyMMddHHmmssSSSS")+getRandom(6);
    }

    /**
     * 获取时间随机ID
     * @return
     */
    public static String getRandomTimeID(){
        return new Date().getTime()+getRandom(6);
    }

}
