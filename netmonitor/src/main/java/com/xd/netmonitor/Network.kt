package com.xd.netmonitor

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by demi on 2019/5/22 下午2:18.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
annotation class Network(val netType: NetworkType = NetworkType.NETWORK_NONE)
