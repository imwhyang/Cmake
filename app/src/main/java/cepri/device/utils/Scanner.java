package cepri.device.utils;


public class Scanner {
    static
    {
        System.loadLibrary("cepri_EMOT500-M55");
    }

    public static native int DeInit();

    /**
     *
     * @param paramInt1 超时时间，单位为毫秒
     * @param paramArrayOfByte 数组地址，解码后的数据存储位置
     * @param paramInt2 偏移量
     * @param paramInt3  数据数量
     * @return
     */
    public static native int Decode(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);

    public static native int Init();

    public int Sacnner_Init()
    {
        return Init();
    }

    public int Scanner_DeInit()
    {
        return DeInit();
    }

    /**
     *
     * @param paramInt1 超时时间，单位为毫秒
     * @param paramArrayOfByte 数组地址，解码后的数据存储位置
     * @param paramInt2 偏移量
     * @param paramInt3  数据数量
     * @return
     */
    public int Scanner_Decode(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
    {
        return Decode(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
    }
}
