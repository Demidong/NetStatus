package com.xd.netmonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.xd.netmonitor.Constants.TAG
import java.util.*

/**
 * Created by demi on 2019/5/22 下午2:22.
 */
class NetBroadcastReceiver : BroadcastReceiver() {
    private var netType: NetworkType
    private val mNetWorkMap: HashMap<Any, List<MethodManager>>

    init {
        netType = NetworkType.NETWORK_NONE
        mNetWorkMap = HashMap()
    }

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.action?.let {
            netType = NetWorkUtils.netWorkType
            if (intent.action.equals(Constants.ANDROID_NET_CHANGE_ACTION, ignoreCase = true)) {
                if (NetWorkUtils.isNetWorkAvailable) {
                    Log.e(TAG, "网络连接成功")
                } else {
                    Log.e(TAG, "网络已经断开")
                }
            }
            Log.e(TAG, "当前网络状态$netType")
            post(netType)
        }

    }

    private fun post(netType: NetworkType) {
        val objs = mNetWorkMap.keys
        for (obj in objs) {
            val managers = mNetWorkMap[obj]
            managers?.let {
                for (manager in it) {
                    if (manager.type.isAssignableFrom(netType::class.java)) {
                        when (manager.networkType) {
                            NetworkType.NETWORK_WIFI -> {
                                if (netType == NetworkType.NETWORK_WIFI || netType == NetworkType.NETWORK_NONE) manager.method.invoke(obj, netType)
                            }
                            NetworkType.NETWORK_4G -> {
                                if (netType == NetworkType.NETWORK_4G || netType == NetworkType.NETWORK_NONE) manager.method.invoke(obj, netType)
                            }
                            NetworkType.NETWORK_3G -> {
                                if (netType == NetworkType.NETWORK_3G || netType == NetworkType.NETWORK_NONE) manager.method.invoke(obj, netType)
                            }
                            NetworkType.NETWORK_2G -> {
                                if (netType == NetworkType.NETWORK_2G || netType == NetworkType.NETWORK_NONE) manager.method.invoke(obj, netType)
                            }
                            else -> {
                                manager.method.invoke(obj, netType)
                            }
                        }
                    }
                }
            }
        }
    }


    fun register(obj: Any) {
        var list: List<MethodManager>? = mNetWorkMap[obj]
        if (list == null) {
            list = findAnnotationMethods(obj)
            mNetWorkMap[obj] = list
        }

    }

    fun unRegister(obj: Any) {
        val list = mNetWorkMap[obj]
        if (list != null) {
            mNetWorkMap.remove(obj)
        }
    }

    private fun findAnnotationMethods(obj: Any): List<MethodManager> {
        val list = ArrayList<MethodManager>()
        val cls = obj.javaClass
        val methods = cls.methods

        for (method in methods) {
            val network = method.getAnnotation(Network::class.java) ?: continue
            val paramTypes = method.parameterTypes
            if(paramTypes.isNotEmpty()){
                val manager = MethodManager(paramTypes[0], network.netType, method)
                list.add(manager)
            }
        }
        return list
    }
}

