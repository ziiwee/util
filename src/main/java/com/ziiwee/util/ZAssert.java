package com.ziiwee.util;

/**
 * Created by ziiwee on 2016/12/5.
 */
public class ZAssert {

    public static void notNull(Object object) throws Exception {
        notNull(object, null);
    }

    public static void notNull(Object object, String message) throws Exception {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(String text) throws Exception {
        hasLength(text, null);
    }

    public static void hasLength(String text, String message) throws Exception {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }


}
