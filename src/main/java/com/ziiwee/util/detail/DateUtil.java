package com.ziiwee.util.detail;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ziiwee on 2016/11/8.
 */
public class DateUtil extends DateUtils {

    /**
     * 格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        return date != null ? new SimpleDateFormat(format).format(date) : "";
    }

    /**
     * 格式化（yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化（yyyy-MM-dd）
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 格式化（yyyy-MM）
     *
     * @param date
     * @return
     */
    public static String formatMonth(Date date) {
        return format(date, "yyyy-MM");
    }

    /**
     * 格式化（yyyy-MM-dd HH:mm）
     *
     * @param date
     * @return
     */
    public static String formatMinute(Date date) {
        return format(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 字符串转换为日期类型
     *
     * @param date
     * @param format
     * @return
     */
    public static Date parse(String date, String format) {
        try {
            return date != null ? new SimpleDateFormat(format).parse(date) : null;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 字符串转换为日期类型（yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd）
     *
     * @param value
     * @return
     */
    public static Date parse(String value) {
        Date date;
        try {
            date = parse(value, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            date = parse(value, "yyyy-MM-dd");
        }
        return date;
    }

    /**
     * 字符串转换为日期类型（yyyy-MM-dd）
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }
}
