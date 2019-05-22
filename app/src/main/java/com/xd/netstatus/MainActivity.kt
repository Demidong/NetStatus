package com.xd.netstatus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.xd.netmonitor.NetWorkMonitor
import com.xd.netmonitor.Network
import com.xd.netmonitor.NetworkType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetWorkMonitor.getInstance().register(this)
    }

    @Network(netType = NetworkType.NETWORK_WIFI)
    fun setUI(type: NetworkType) {
        current_net.text = "当前网络为$type"
    }

    @Network(netType = NetworkType.NETWORK_AUTO)
    fun monitorNetwork(type: NetworkType) {
        Log.e("monitorNetwork","当前网络为$type")
    }

    @Network(netType = NetworkType.NETWORK_AUTO)
    fun mNetwork(s:String,type: NetworkType) {
        Log.e("mNetwork","当前网络为$type")
    }

    override fun onDestroy() {
        super.onDestroy()
        NetWorkMonitor.getInstance().unRegister(this)
    }
}
