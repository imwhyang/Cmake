package cepri.device.utils.util;

/**
 * Author  WenHaiyang
 * Date    2018/7/2 15:24.
 * Desc
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    public static int strToInt(String str) {
        if (isBlank(str)) return 0;
        return Integer.valueOf(str);

    }

}
