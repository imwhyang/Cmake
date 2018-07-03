package cepri.device.utils;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.easygold.cmake.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cepri.device.utils.activity.RS485Activity;
import cepri.device.utils.activity.ScannerActivity;
import cepri.device.utils.activity.SecurityunitActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    private static final String TAG = "MainActivity";
    @BindView(R.id.bt_scan)
    Button mBtScan;
    @BindView(R.id.bt_securityunit)
    Button mBtSecurityunit;
    @BindView(R.id.bt_r_esam)
    Button mBtREsam;
    @BindView(R.id.bt_irda)
    Button mBtIrda;
    @BindView(R.id.bt_laserirda)
    Button mBtLaserirda;
    @BindView(R.id.bt_rs485)
    Button mBtRs485;
    private int init;
    private boolean isInit;
    private TextView tv;
    private Scanner mScanner;
    private int mScanOffset = 0;
    private byte[] mScanByte = new byte[120];
    private int mScanLimit = mScanByte.length;
    private Handler mHandler;
    private Runnable scanRunnable;
    private boolean isScaning = true;
    private StringBuffer mResult = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bt_scan, R.id.bt_securityunit, R.id.bt_r_esam, R.id.bt_irda, R.id.bt_laserirda, R.id.bt_rs485})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scan:
                ScannerActivity.goScannerActivity(this,null);
                break;
            case R.id.bt_securityunit:
                SecurityunitActivity.goSecurityunitActivity(this,null);
                break;
            case R.id.bt_r_esam:
                break;
            case R.id.bt_irda:
                break;
            case R.id.bt_laserirda:
                break;
            case R.id.bt_rs485:
                RS485Activity.goRS485Activity(this,null);
                break;
        }
    }
}
