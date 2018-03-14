package com.sample.app.content;

import android.util.Log;

/**
 * 作者：蒙景博
 * 时间：2018/3/14
 * 描述：
 */
public class ContentPresenter extends ContentContract.Presenter {

    private static final String TAG = ContentPresenter.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e(TAG, "Presenter ----- onCreate()");
    }

    @Override
    public void onStart() {
        Log.e(TAG, "Presenter ----- onStart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "Presenter ----- onResume()");
    }

    @Override
    public void onStop() {
        Log.e(TAG, "Presenter ----- onStop()");
    }

    @Override
    public void onPause() {
        Log.e(TAG, "Presenter ----- onPause()");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "Presenter ----- onDestroy()");
    }
}
