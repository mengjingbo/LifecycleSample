## Android Lifecycle-Aware Components
### 关于Lifecycle

**Lifecycle** 是一个持有(如 **Activity** 或 **Fragment** )生命周期状态信息的类，并且允许其它对象观察这个状态。从而在外部实现监听，能够使我们及时释放资源，避免内存泄露和程序崩溃。

### 为什么要使用Lifecycle？

常见的观察生命周期的方式，如MVP中在 **Presenter** 中对 **Activity** 或 **Fragment** 的生命周期进行观察，我们要在 **Presenter** 中定义方法，在 **Activity** 或 **Fragment** 中调用，通过这种方式进行观察，这样代码量大而且也比较繁琐，使用 **Lifecycle** 组件就能简化很多，下面通过 **Lifecycle** 与 **Presenter** 管理生命周期的方式进行比较。

##### Presenter管理生命周期

在 **Presenter** 中处理 **Activity** 和 **Fragment** 的生命周期，如下：

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

##### Lifecycle管理生命周期

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

可以看到代码量少了很多，简化了很多，观察生命周期也比较简单。
