package com.example.common.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
/**
 * @ClassName Base64Util
 * @Description
 * @Author perth
 * @Date 2018/12/26 0026 上午 6:10
 * @Version 1.0
 **/



public class Base64Util {

    /**
     * 编码字符串
     * @param str
     * @return
     */
    public static String encode(String str){
        if(StringUtils.isEmpty(str))
        {return "";}
        return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * 解码字符串
     * @param str
     * @return
     */
    public static String decode(String str){
        if(StringUtils.isEmpty(str)) {return "";}
        return new String(Base64.decodeBase64(str));
    }
}
