package com.sample.app.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * 作者：蒙景博
 * 时间：2018/3/13
 * 描述：
 */
public class MainLifecycleListener implements LifecycleObserver {

    private static final String TAG = MainLifecycleListener.class.getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e(TAG, "LifecycleObserver ----- onCreate()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e(TAG, "LifecycleObserver ----- onStart()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e(TAG, "LifecycleObserver ----- onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e(TAG, "LifecycleObserver ----- onStop()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e(TAG, "LifecycleObserver ----- onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e(TAG, "LifecycleObserver ----- onDestroy()");
    }

    /**
     * 每次生命周期发生变化时都会被触发
     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    public void onAny(){
//        Log.e(TAG, "LifecycleObserver ----- onAny()");
//    }
}
