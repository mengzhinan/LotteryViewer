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

    fun getSharedPreferences(context: Context?): SharedPreferences? {
        return context?.applicationContext?.getSharedPreferences(
            BASE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    internal fun getEditor(context: Context?): SharedPreferences.Editor? {
        return getSharedPreferences(context)?.edit()
    }

    fun contains(context: Context?, key: String?): Boolean {
        return getSharedPreferences(context)?.contains(key) ?: false
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

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    fun putApply(
        context: Context?,
        key: String?,
        value: Any?,
        isCommit: Boolean = false,
        listener: SharedPreferences.OnSharedPreferenceChangeListener? = null
    ) {
        key ?: return
        value ?: return
        val sp = getSharedPreferences(context) ?: return
        if (listener != null) {
            sp.registerOnSharedPreferenceChangeListener(listener)
        }
        var editor: SharedPreferences.Editor? = null
        if (value is Boolean) {
            editor = sp.edit()?.putBoolean(key, value)
        } else if (value is Float) {
            editor = sp.edit()?.putFloat(key, value)
        } else if (value is Int) {
            editor = sp.edit()?.putInt(key, value)
        } else if (value is Long) {
            editor = sp.edit()?.putLong(key, value)
        } else if (value is String) {
            editor = sp.edit()?.putString(key, value)
        }
        if (isCommit) {
            editor?.commit()
        } else {
            editor?.apply()
        }
    }

    fun unRegisterSPChangeListener(
        context: Context?,
        listener: SharedPreferences.OnSharedPreferenceChangeListener?
    ) {
        listener ?: return
        getSharedPreferences(context)?.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun clear(context: Context?) {
        getEditor(context)?.clear()
    }

    fun remove(context: Context?, key: String?) {
        getEditor(context)?.remove(key)
    }

}
