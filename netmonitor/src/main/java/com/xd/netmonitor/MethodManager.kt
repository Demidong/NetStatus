package com.xd.netmonitor

import java.lang.reflect.Method

/**
 * Created by demi on 2019/5/22 下午4:51.
 */
class MethodManager(
        var type: Class<*>,//参数的网络类型
        var networkType: NetworkType?,//网络类型
        var method: Method) //需要执行的方法
