package cepri.device.utils.util;

import android.os.Handler;


/**
 * Author  WenHaiyang
 * Date    2018/7/2 14:40.
 * Desc
 */
public abstract class FutureTask {
   Runnable mRun ;
   Runnable mUiRun ;
   Handler mHandler;

  abstract void doInbackground();
  abstract void doInUiThread();


    public FutureTask() {
        mHandler = new Handler() {};
    }
}
