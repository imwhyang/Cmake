package cepri.device.utils.activity;

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
import cepri.device.utils.SecurityUnit;
import cepri.device.utils.util.CommonUtils;
import cepri.device.utils.util.ThreadManager;

public class SecurityunitActivity extends AppCompatActivity {

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
    @BindView(R.id.et_mode)
    EditText mEtMode;
    @BindView(R.id.et_speed)
    EditText mEtSpeed;
    @BindView(R.id.et_halfword)
    EditText mEtHalfword;
    @BindView(R.id.bt_SpiConfig)
    Button mBtSpiConfig;
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
    private SecurityUnit mSecurityUnit;

    ThreadManager mThreadManager;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securityunit);
        mSecurityUnit = new SecurityUnit();
        ButterKnife.bind(this);
        mThreadManager = ThreadManager.getInstance();
        mHandler = new Handler();
    }
    public static void goSecurityunitActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SecurityunitActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }
    @OnClick({R.id.bt_on, R.id.bt_off, R.id.bt_config,
            R.id.bt_clearsendcache, R.id.bt_clearrecvcache,R.id.bt_SpiConfig, R.id.bt_send, R.id.bt_recv, R.id.bt_set_timeout_in, R.id.bt_set_timeout_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_on:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i = mSecurityUnit.SecurityUnit_Init();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                    CommonUtils.ToastShow(SecurityunitActivity.this,
                                            getString(i == 0?R.string.ui_tip_success:R.string.ui_tip_fail));
                            }
                        });
                    }
                });

                break;
            case R.id.bt_off:
                mThreadManager.execute(new Runnable() {
                    @Override
                    public void run() {
                        final int i =  mSecurityUnit.SecurityUnit_DeInit();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
//                                0	执行成功。
//                                -1	执行失败。
                                CommonUtils.ToastShow(SecurityunitActivity.this,
                                        getString(i == 0?R.string.ui_tip_success:R.string.ui_tip_fail));
                            }
                        });
                    }
                });


                break;
            case R.id.bt_config:
                break;
            case R.id.bt_SpiConfig:
                break;
            case R.id.bt_send:
                break;
            case R.id.bt_recv:
                break;
            case R.id.bt_set_timeout_in:
                break;
            case R.id.bt_set_timeout_out:
                break;
            case R.id.bt_clearsendcache:
                break;
            case R.id.bt_clearrecvcache:
                break;
        }
    }
}
