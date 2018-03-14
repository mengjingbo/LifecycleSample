## Android Lifecycle-Aware Components

### 关于Lifecycle

Lifecycle架构组件是一个生命周期管理组件，它可以将Activity或Fragment的生命周期分离出来，从而在外部实现监听，能够使我们及时释放资源，避免内存泄露和程序崩溃。

### 为什么要使用Lifecycle？

Lifecycle能更加友好的帮助我们管理Activity或Fragment的生命周期，下面通过Lifecycle与Presenter管理生命周期的方式进行比较。

##### Presenter管理生命周期

常见的MVP架构中Presenter组件处理Activity和Fragment的生命周期，如下：

```Java

// 定义Contract

public interface ContentContract {

    // ......

    abstract class Presenter {

        public abstract void onCreate();

        public abstract void onStart();

        public abstract void onResume();

        public abstract void onStop();

        public abstract void onPause();

        public abstract void onDestroy();
    }
}

// 定义Presenter

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

// Activity中使用

public class ContentActivity extends AppCompatActivity {

    private ContentPresenter mPresenter = new ContentPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
```
在Presenter中对在Activity或Fragment的生命周期进行监听时需要种单独对Presenter中的方法进行处理，但使用Lifecycle组件就会简化很多。

##### LifecycleObserver管理生命周期

需要实现 **LifecycleObserver** 接口，并添加事件注解。

```Java

// 定义Lifecycle

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
```

##### 初始化LifecycleObserver

```Java
public class MainActivity extends AppCompatActivity {

    private MainLifecycleListener mListener = new MainLifecycleListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 添加观察者
        getLifecycle().addObserver(mListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除观察者
        getLifecycle().removeObserver(mListener);
    }
}
```

[查看案例源代码](https://github.com/mengjingbo/LifecycleSample)