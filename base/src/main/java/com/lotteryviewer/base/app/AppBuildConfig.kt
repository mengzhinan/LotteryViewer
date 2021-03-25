package com.lotteryviewer.base.app

import android.os.Bundle

/**
 * @Author: duke
 * @DateTime: 2021-03-17 11:55:01
 * @Description: 功能描述：
 */
object AppBuildConfig {

    private var DEBUG = false
    private var APPLICATION_ID = ""
    private var MAIN_ACTIVITY_NAME = ""
    private var BUILD_TYPE = "debug"
    private var FLAVOR = "none"
    private var VERSION_CODE = 1
    private var VERSION_NAME = "1.0"
    private val EXTRAS = Bundle()

    fun DEBUG(): Boolean {
        return DEBUG
    }

    fun APPLICATION_ID(): String {
        return APPLICATION_ID
    }

    fun MAIN_ACTIVITY_NAME(): String {
        return MAIN_ACTIVITY_NAME
    }

    fun BUILD_TYPE(): String {
        return BUILD_TYPE
    }

    fun FLAVOR(): String {
        return FLAVOR
    }

    fun VERSION_CODE(): Int {
        return VERSION_CODE
    }

    fun VERSION_NAME(): String {
        return VERSION_NAME
    }

    fun EXTRAS(): Bundle {
        return EXTRAS
    }

    fun <T> EXTRA(key: String?): T? {
        return EXTRAS[key] as T?
    }

    fun newBuilder(): Builder {
        return Builder()
    }

    class Builder {
        fun DEBUG(`val`: Boolean): Builder {
            DEBUG = `val`
            return this
        }

        fun APPLICATION_ID(`val`: String): Builder {
            APPLICATION_ID = `val`
            return this
        }

        fun MAIN_ACTIVITY_NAME(`val`: String): Builder {
            MAIN_ACTIVITY_NAME = `val`
            return this
        }

        fun BUILD_TYPE(`val`: String): Builder {
            BUILD_TYPE = `val`
            return this
        }

        fun FLAVOR(`val`: String): Builder {
            FLAVOR = `val`
            return this
        }

        fun VERSION_CODE(`val`: Int): Builder {
            VERSION_CODE = `val`
            return this
        }

        fun VERSION_NAME(`val`: String): Builder {
            VERSION_NAME = `val`
            return this
        }

        fun EXTRAS(bundle: Bundle?): Builder {
            EXTRAS.putAll(bundle)
            return this
        }
    }
}