package cepri.device.utils.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author  WenHaiyang
 * Date    2018/7/2 14:25.
 * Desc
 */
public class CommonUtils {
    public static void ToastShow(Context context, String string) {
        Toast.makeText(context, string,Toast.LENGTH_SHORT).show();
    }
    public static void ToastShow(Context context, int string) {
        ToastShow(context,context.getString(string));
    }

    public static byte[] hexStringToByte(String paramString)
    {
        int j = paramString.length() / 2;
        byte[] arrayOfByte = new byte[j];
        char[] paramStrings = paramString.toCharArray();
        int i = 0;
        while (i < j)
        {
            int k = i * 2;
            arrayOfByte[i] = ((byte)(toByte(paramStrings[k]) << 4 | toByte(paramStrings[(k + 1)])));
            i += 1;
        }
        return arrayOfByte;
    }

    private static byte toByte(char paramChar)
    {
        return (byte)"0123456789ABCDEF".indexOf(paramChar);
    }


    public static final String bytesToHexString(byte[] paramArrayOfByte)
    {
        return bytesToHexString(paramArrayOfByte, paramArrayOfByte.length);
    }

    public static final String bytesToHexString(byte[] paramArrayOfByte, int paramInt)
    {
        StringBuffer localStringBuffer = new StringBuffer(paramInt);
        int i = 0;
        while (i < paramInt)
        {
            String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
            if (str.length() < 2) {
                localStringBuffer.append(0);
            }
            localStringBuffer.append(str.toUpperCase());
            i += 1;
        }
        return localStringBuffer.toString();
    }

}
