package cepri.device.utils;


public class SecurityUnit {

    static
    {
        System.loadLibrary("cepri_EMOT500-M55");
    }

    public static native int DeInit();

    public static native int Init();
    public static native int ClearSendCache();
    public static native int ClearRecvCache();
    public static native int Config(int baudrate, int databits, int parity, int stopbits,int blockmode);
    public static native int SpiConfig(int mode, int speed, int halfword);
    public static native int SetTimeOut(int direction, int timeout);

    public static native int RecvData(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    public static native int SendData(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    /**
     * 电源控制、关闭端口
     * @return
     */
    public int SecurityUnit_DeInit()
    {
        return DeInit();
    }

    /**
     * 电源控制、打开端口
     * @return
     */
    public int SecurityUnit_Init()
    {
        return Init();
    }

    /**
     * 清除发送缓存
     * @return
     */
    public int SecurityUnit_ClearSendCache()
    {
        return ClearSendCache();
    }

    /**
     * 清除接收缓存
     * @return
     */
    public int SecurityUnit_ClearRecvCache()
    {
        return ClearRecvCache();
    }

    /**
     *
     * @param paramArrayOfByte 数组地址
     * @param Offset 偏移量
     * @param Count 数据数量
     * @return
     */
    public int SecurityUnit_RecvData(byte[] paramArrayOfByte, int Offset, int Count)
    {
        return RecvData(paramArrayOfByte, Offset, Count);
    }

    /**
     *
     * @param paramArrayOfByte 数组地址
     * @param Offset 偏移量
     * @param Count 数据数量
     * @return
     */
    public int SecurityUnit_SendData(byte[] paramArrayOfByte, int Offset, int Count)
    {
        return SendData(paramArrayOfByte, Offset, Count);
    }

    /**
     * 设置通讯参数
     * @param baudrate 通讯波特率。
     * @param databits  数据位。
     * @param parity     校验位。0为无校验，1为奇校验，2为偶校验，3为Mark校验，4为Space校验。
     * @param stopbits    停止位。0为无停止位，1为1位停止位，2为2位停止位，3为1.5位停止位。
     * @param blockmode     阻塞模式。0为无阻塞，1为阻塞
     * @return
     */
    public int SecurityUnit_Config(int baudrate, int databits, int parity, int stopbits,int blockmode)
    {
        return Config( baudrate,  databits,  parity,  stopbits, blockmode);
    }

    /**
     * 设置通信参数
     * @param mode SPI模式。0，1，2，3分别对应模式0，1，2，3
     * @param speed  SPI时钟。单位为Hz，范围为0－18M。
     * @param halfword   传输模式。 0为8位，1为16位。
     * @return
     */
    public int SecurityUnit_SpiConfig(int mode, int speed, int halfword)
    {
        return SpiConfig( mode,  speed,  halfword);
    }

    /**
     * 设置发送和接收数据的超时时间
     * @param direction 方向 0，	发送；1,接收
     * @param timeout 超时时间 单位 ms(毫秒)
     * @return
     */
    public int SecurityUnit_SetTimeOut(int direction, int timeout)
    {
        return SetTimeOut( direction,  timeout);
    }
}
