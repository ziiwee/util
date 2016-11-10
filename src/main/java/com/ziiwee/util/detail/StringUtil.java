package com.ziiwee.util.detail;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by ziiwee on 2016/11/8.
 */
public class StringUtil extends StringUtils{

    /**
     * 是否为中文格式
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        return str.matches("[\u4e00-\u9fa5]*");
    }

    /**
     * 是否为EMAIL格式
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        return str.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }

    /**
     * 是否为手机号码格式
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        return str.matches("^[1][3,5,8][0-9]{9}$");
    }

    /**
     * 转换编码
     * @param str
     * @param oldCharset
     * @param newCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String convertCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        return new String(str.getBytes(oldCharset), newCharset);
    }

    /**
     * 使字符串应用到HTML
     * @param str
     * @return
     */
    public static String fixHTML(String str) {
        str = str.replaceAll("\r", "");
        str = str.replaceAll("\n", "");
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
}
