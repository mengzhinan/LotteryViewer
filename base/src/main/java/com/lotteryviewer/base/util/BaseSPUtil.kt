package com.lotteryviewer.base.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * @Author: duke
 * @DateTime: 2021-03-24 16:14:44
 * @Description: 功能描述：
 *
 */
object BaseSPUtil {

    private const val BASE_FILE_NAME = "lottery_viewer"

    private fun getSharedPreferences(context: Context?): SharedPreferences? {
        return context?.applicationContext?.getSharedPreferences(
            BASE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getBoolean(context: Context?, key: String?, defaultValue: Boolean): Boolean {
        return getSharedPreferences(context)?.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun getString(context: Context?, key: String?, defaultValue: String): String {
        return getSharedPreferences(context)?.getString(key, defaultValue) ?: defaultValue
    }

    fun getInt(context: Context?, key: String?, defaultValue: Int): Int {
        return getSharedPreferences(context)?.getInt(key, defaultValue) ?: defaultValue
    }

    fun getLong(context: Context?, key: String?, defaultValue: Long): Long {
        return getSharedPreferences(context)?.getLong(key, defaultValue) ?: defaultValue
    }

    fun getFloat(context: Context?, key: String?, defaultValue: Float): Float {
        return getSharedPreferences(context)?.getFloat(key, defaultValue) ?: defaultValue
    }

    @SuppressLint("CommitPrefEdits")
    fun putApply(
        context: Context?,
        key: String?,
        value: Any?,
        listener: SharedPreferences.OnSharedPreferenceChangeListener?
    ) {
        key ?: return
        value ?: return
        val sp = getSharedPreferences(context) ?: return
        if (listener != null) {
            sp.registerOnSharedPreferenceChangeListener(listener)
        }
        if (value is Boolean) {
            sp.edit()?.putBoolean(key, value)?.apply()
        } else if (value is Float) {
            sp.edit()?.putFloat(key, value)?.apply()
        } else if (value is Int) {
            sp.edit()?.putInt(key, value)?.apply()
        } else if (value is Long) {
            sp.edit()?.putLong(key, value)?.apply()
        } else if (value is String) {
            sp.edit()?.putString(key, value)?.apply()
        }
    }

    fun unRegisterSPChangeListener(
        context: Context?,
        listener: SharedPreferences.OnSharedPreferenceChangeListener?
    ) {
        listener ?: return
        getSharedPreferences(context)?.unregisterOnSharedPreferenceChangeListener(listener)
    }

    @SuppressLint("CommitPrefEdits")
    fun clear(context: Context?) {
        getSharedPreferences(context)?.edit()?.clear()
    }

}
