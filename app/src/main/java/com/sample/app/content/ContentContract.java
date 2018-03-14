package com.sample.app.content;

/**
 * 作者：蒙景博
 * 时间：2018/3/14
 * 描述：
 */
public interface ContentContract {

    interface View {

    }

    interface Model {

    }

    abstract class Presenter {

        public abstract void onCreate();

        public abstract void onStart();

        public abstract void onResume();

        public abstract void onStop();

        public abstract void onPause();

        public abstract void onDestroy();
    }
}
