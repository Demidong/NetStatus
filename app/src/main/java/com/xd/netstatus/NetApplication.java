package com.xd.netstatus;

import android.app.Application;

import com.xd.netmonitor.NetWorkMonitor;

/**
 * Created by demi on 2019/5/22 下午4:29.
 */
public class NetApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkMonitor.getInstance().init(this);
    }
}
