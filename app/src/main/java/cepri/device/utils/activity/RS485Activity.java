package cepri.device.utils.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.easygold.cmake.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cepri.device.utils.RS485;
import cepri.device.utils.util.CommonUtils;
import cepri.device.utils.util.StringUtils;
import cepri.device.utils.util.ThreadManager;

import static cepri.device.utils.util.CommonUtils.ToastShow;
import static cepri.device.utils.util.CommonUtils.bytesToHexString;
import static cepri.device.utils.util.CommonUtils.hexStringToByte;

public class RS485Activity extends AppCompatActivity {

    @BindView(R.id.bt_on)
    Button mBtOn;
    @BindView(R.id.bt_off)
    Button mBtOff;
    @BindView(R.id.et_baudrate)
    EditText mEtBaudrate;
    @BindView(R.id.et_databits)
    EditText mEtDatabits;
    @BindView(R.id.et_parity)
    EditText mEtParity;
    @BindView(R.id.et_stopbits)
    EditText mEtStopbits;
    @BindView(R.id.et_blockmode)
    EditText mEtBlockmode;
    @BindView(R.id.bt_config)
    Button mBtConfig;
    @BindView(R.id.et_output)
    EditText mEtOutput;
    @BindView(R.id.et_Count)
    EditText mEtCount;
    @BindView(R.id.bt_send)
    Button mBtSend;
    @BindView(R.id.et_rcCount)
    EditText mEtRcCount;
    @BindView(R.id.tv_revc_info)
    TextView mTvRevcInfo;
    @BindView(R.id.bt_recv)
    Button mBtRecv;
    @BindView(R.id.et_timeout_in)
    EditText mEtTimeoutIn;
    @BindView(R.id.et_timeout_out)
    EditText mEtTimeoutOut;
    @BindView(R.id.bt_set_timeout_in)
    Button mBtSetTimeoutIn;
    @BindView(R.id.bt_set_timeout_out)
    Button mBtSetTimeoutOut;
    @BindView(R.id.bt_clearsendcache)
    Button mBtClearsendcache;
    @BindView(R.id.bt_clearrecvcache)
    Button mBtClearrecvcache;
    private Activity mAct;
    private ThreadManager mThreadManager;
    private Handler mHandler;
    private RS485 mRS485;
    private static final String TAG = "RS485Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rs485);
        mAct = this;
        ButterKnife.bind(this);
        mThreadManager = ThreadManager.getInstance();
        mHandler = new Handler();
        mRS485 = new RS485();
    }

    public static void goRS485Activity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RS485Activity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);

    }

    @OnClick({R.id.bt_on, R.id.bt_off, R.id.bt_config, R.id.bt_send, R.id.bt_recv, R.id.bt_set_timeout_in, R.id.bt_set_timeout_out, R.id.bt_clearsendcache, R.id.bt_clearrecvcache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_on:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.RS485_Init();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
            case R.id.bt_off:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.RS485_DeInit();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
            case R.id.bt_config:
                final String baudrate = mEtBaudrate.getText().toString().trim();
                final String databits = mEtDatabits.getText().toString().trim();
                final String parity = mEtParity.getText().toString().trim();
                final String stopbits = mEtStopbits.getText().toString().trim();
                final String blockmode = mEtBlockmode.getText().toString().trim();
                if (
                        StringUtils.isBlank(baudrate) ||
                                StringUtils.isBlank(databits) ||
                                StringUtils.isBlank(parity) ||
                                StringUtils.isBlank(stopbits) ||
                                StringUtils.isBlank(blockmode)
                        ) {

                    CommonUtils.ToastShow(mAct, "请确认信息");
                    return;
                }

                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.RS485_Config(StringUtils.strToInt(baudrate),
                                StringUtils.strToInt(databits), StringUtils.strToInt(parity),
                                StringUtils.strToInt(stopbits),
                                StringUtils.strToInt(blockmode)
                        );
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });

                break;
            case R.id.bt_send:
                final String output = mEtOutput.getText().toString().trim();
                if (StringUtils.isBlank(output)) {
                    ToastShow(mAct, "请输入发送内容");
                    return;
                }
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        byte[] paramAnonymousView = hexStringToByte(output);
                        int j = paramAnonymousView.length;
//                        int i = 0;
//                        while (i < j) {
//                            Log.i(TAG, "cmdBuf [" + i + "] = " + Integer.toHexString(paramAnonymousView[i] & 0xFF));
//                            i += 1;
//                        }
                        final int result = mRS485.RS485_SendData(paramAnonymousView, 0, j);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(result == 0 ? R.string.ui_tip_success : result == 1 ? R.string.ui_tip_port_error : R.string.ui_tip_fail));
                            }
                        });
                    }
                });

                break;
            case R.id.bt_recv:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {

                        byte[] paramAnonymousView = new byte[64];
                        int j = paramAnonymousView.length;
                        mRS485.RS485_RecvData(paramAnonymousView, 0, j);
                        int i = 0;

                        final String resultStr = bytesToHexString(paramAnonymousView);
                        final StringBuffer strBuffer = new StringBuffer();
                        while (i < j) {
//                    Log.i(TAG, "revBuf [" + i + "] = " + Integer.toHexString(paramAnonymousView[i] & 0xFF));
                            strBuffer.append("revBuf [" + i + "] = " + Integer.toHexString(paramAnonymousView[i] & 0xFF));
                            i += 1;
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                mTvRevcInfo.setText(resultStr + "\n" + strBuffer);
                            }
                        });
                    }
                });
                break;
            case R.id.bt_set_timeout_in:
                final String timein = mEtTimeoutIn.getText().toString().trim();
                if (StringUtils.isBlank(timein)) {
                    ToastShow(mAct,R.string.ui_tip_make_sure);
                    return;
                }
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.SetTimeOut(1, StringUtils.strToInt(timein));
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
            case R.id.bt_set_timeout_out:
                final String timeout = mEtTimeoutOut.getText().toString().trim();
                if (StringUtils.isBlank(timeout)) {
                    ToastShow(mAct,R.string.ui_tip_make_sure);
                    return;
                }
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.SetTimeOut(4, StringUtils.strToInt(timeout));
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
            case R.id.bt_clearsendcache:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.RS485_ClearSendCache();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
            case R.id.bt_clearrecvcache:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mRS485.RS485_ClearRecvCache();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(mAct, getString(i == 0 ? R.string.ui_tip_success : R.string.ui_tip_fail));
                            }
                        });
                    }
                });
                break;
        }
    }
}
