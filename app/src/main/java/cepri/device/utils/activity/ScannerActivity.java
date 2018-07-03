package cepri.device.utils.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.easygold.cmake.R;

import java.util.Date;

import cepri.device.utils.Scanner;

public class ScannerActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    private static final String TAG = "MainActivity";
    private int init;
    private boolean isInit;
    private TextView tv;
    private Scanner mScanner;
    private int mScanOffset =0;
    private byte[] mScanByte = new byte[120];
    private int mScanLimit = mScanByte.length;
    private Handler mHandler;
    private Runnable scanRunnable;
    private boolean isScaning =true;
    private StringBuffer mResult = new StringBuffer();
    private Activity mAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        mScanner = new Scanner();
        mAct = this;
        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        mResult.append("Scanner Result: ");
        tv.setText(mResult);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        tv.setText(mResult.append(new String(mScanByte)+"\n"));
                        break;
                    case -1:
                        Toast.makeText(mAct, "scanner error", Toast.LENGTH_SHORT);
                        break;
                }
            }
        };
        scanRunnable = new Runnable() {
            @Override
            public void run() {
                while (isScaning) {
                    try {
                        int initResult = mScanner.Sacnner_Init();
                        if (initResult == 0) {
                            int decodeResult = mScanner.Scanner_Decode(5000, mScanByte, mScanOffset, mScanLimit);
                            if (decodeResult == 0) {
                                Message message = Message.obtain();
                                mHandler.sendEmptyMessage(0);
                            }
                        }
                        mHandler.sendEmptyMessage(-1);
                        Thread.sleep(1000);
                        Log.d(TAG, "run: scanner "+new Date().getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


    }

    public static void goScannerActivity(Context context,Bundle bundle) {
        Intent intent = new Intent(context, ScannerActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);

    }
    @Override
    public void onClick(View view) {
        synchronized (this){
            switch (view.getId()) {
                case R.id.bt_close:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mScanner.Scanner_DeInit();
                        }
                    }).start();
                    break;
                case R.id.bt_start:
                    new Thread(scanRunnable).start();
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isScaning = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mScanner.Scanner_DeInit();
            }
        }).start();
    }
}
