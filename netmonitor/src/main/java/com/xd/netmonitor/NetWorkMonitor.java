package com.xd.netmonitor;


import android.app.Application;
import android.content.IntentFilter;

import static com.xd.netmonitor.Constants.ANDROID_NET_CHANGE_ACTION;

/**
 * Created by demi on 2019/5/22 下午2:09.
 */
public class NetWorkMonitor {

    private Application application;
    private NetBroadcastReceiver mBroadcastReceiver;

    private NetWorkMonitor() {
        mBroadcastReceiver = new NetBroadcastReceiver();
    }

    public static NetWorkMonitor getInstance() {
        return NetWorkManagerHolder.monitor;
    }

    //静态内部类单例模式
    private static class NetWorkManagerHolder {
        private static final NetWorkMonitor monitor = new NetWorkMonitor();
    }

    public void init(Application application) {
        this.application = application;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INSTANCE.getANDROID_NET_CHANGE_ACTION());
        application.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public Application getApplication() {
        return application;
    }

    public void register(Object object) {
        mBroadcastReceiver.register(object);
    }

    public void unRegister(Object object) {
        mBroadcastReceiver.unRegister(object);
    }

}
