package cepri.device.utils;


public class RS485 {
    static {
        System.loadLibrary("cepri_EMOT500-M55");
    }

    public static native int Config(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

    public static native int DeInit();

    public static native int Init();

    public static native int ClearSendCache();

    public static native int ClearRecvCache();
    public static native int SetTimeOut(int direction,int timeout);

    public static native int RecvData(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    public static native int SendData(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    /**
     * 设置通讯参数
     * @param baudrate
     * @param databits
     * @param parity
     * @param stopbits
     * @param blockmode
     * @return
     */
    public int RS485_Config(int baudrate, int databits, int parity, int stopbits, int blockmode) {
        return Config(baudrate, databits, parity, stopbits, blockmode);
    }

    public int RS485_DeInit() {
        return DeInit();
    }

    public int RS485_ClearRecvCache() {
        return ClearRecvCache();
    }

    public int RS485_ClearSendCache() {
        return ClearSendCache();
    }

    public int RS485_Init() {
        return Init();
    }

    public int RS485_RecvData(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        return RecvData(paramArrayOfByte, paramInt1, paramInt2);
    }

    public int RS485_SendData(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        return SendData(paramArrayOfByte, paramInt1, paramInt2);
    }

    /**
     *
     * @param direction  方向0，	发送；1,接收
     * @param timeout
     * @return
     */
    public int RS485_SetTimeOut(int direction, int timeout) {
        return SetTimeOut( direction, timeout);
    }
}
