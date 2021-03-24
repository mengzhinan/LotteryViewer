package com.lotteryviewer.base.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.lotteryviewer.base.util.TextUtil.isNullOrEmpty
import java.text.DateFormat
import java.text.ParseException
import java.util.*

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:19:32
 * @Description: 功能描述：
 */
object BasePreferenceHelper {
    /**
     * isFirstTime 的一个通用方法
     * 对于同一个 key 只会为 true 一次
     */
    fun isFirstTime(context: Context?, key: Int): Boolean {
        if (getBoolean(context, key, true)) {
            putBoolean(context, key, false)
            return true
        }
        return false
    }

    //<editor-fold desc="Preference 的一些基本操作">
    fun putInt(pContext: Context?, pKeyResId: Int, pValue: Int) {
        if (pContext == null) {
            return
        }
        editor(pContext).putInt(getKey(pContext, pKeyResId), pValue).apply()
    }

    fun getInt(pContext: Context?, pKeyResId: Int, pDefValue: Int): Int {
        return if (pContext == null) pDefValue else pref(pContext).getInt(
            getKey(
                pContext,
                pKeyResId
            ), pDefValue
        )
    }

    fun putLong(pContext: Context?, pKeyResId: Int, pValue: Long) {
        if (pContext == null) {
            return
        }
        editor(pContext).putLong(getKey(pContext, pKeyResId), pValue).apply()
    }

    fun getLong(pContext: Context?, pKeyResId: Int, pDefValue: Long): Long {
        return if (pContext == null) pDefValue else pref(pContext).getLong(
            getKey(
                pContext,
                pKeyResId
            ), pDefValue
        )
    }

    fun putBoolean(pContext: Context?, pKeyResId: Int, pValue: Boolean) {
        if (pContext == null) {
            return
        }
        editor(pContext).putBoolean(getKey(pContext, pKeyResId), pValue).apply()
    }

    fun getBoolean(pContext: Context?, pKeyResId: Int, pDefValue: Boolean): Boolean {
        return if (pContext == null) pDefValue else pref(pContext).getBoolean(
            pContext.resources.getString(pKeyResId),
            pDefValue
        )
    }

    fun putFloat(pContext: Context?, pKeyResId: Int, pValue: Float) {
        if (pContext == null) {
            return
        }
        editor(pContext).putFloat(getKey(pContext, pKeyResId), pValue).apply()
    }

    fun getFloat(pContext: Context?, pKeyResId: Int, pDefValue: Float): Float {
        return if (pContext == null) pDefValue else pref(pContext).getFloat(
            pContext.resources.getString(pKeyResId),
            pDefValue
        )
    }

    fun putString(pContext: Context?, pKeyResId: Int, pValue: String?) {
        if (pContext == null) {
            return
        }
        editor(pContext).putString(getKey(pContext, pKeyResId), pValue).apply()
    }

    fun putString(pContext: Context?, pKey: String?, pValue: String?) {
        if (pContext == null) {
            return
        }
        editor(pContext).putString(pKey, pValue).apply()
    }

    fun getString(context: Context?, keyResId: Int, defValue: String?): String? {
        return if (context == null) defValue else pref(context).getString(
            context.resources.getString(keyResId),
            defValue
        )
    }

    fun getString(pContext: Context?, pKey: String?, pDefValue: String?): String? {
        return pref(pContext).getString(pKey, pDefValue)
    }

    fun putTime(
        pContext: Context?, pKeyResId: Int, pTime: GregorianCalendar,
        dateFormat: DateFormat
    ) {
        if (pContext == null) {
            return
        }
        val str = dateFormat.format(pTime.time)
        editor(pContext).putString(getKey(pContext, pKeyResId), str).apply()
    }

    fun getTime(
        pContext: Context?, pKeyResId: Int, pDefValueStr: String?,
        dateFormat: DateFormat
    ): GregorianCalendar? {
        val str = getString(pContext, pKeyResId, pDefValueStr)
        if (!isNullOrEmpty(str)) {
            try {
                val time = GregorianCalendar()
                time.time = dateFormat.parse(str)
                return time
            } catch (pE: ParseException) {
                pE.printStackTrace()
            }
        }
        return null
    }

    fun remove(pContext: Context, pKeyResId: Int) {
        editor(pContext).remove(getKey(pContext, pKeyResId)).apply()
    }

    fun remove(pContext: Context?, pKey: String?) {
        if (contains(pContext, pKey)) {
            editor(pContext).remove(pKey).apply()
        }
    }

    fun contains(pContext: Context, pKeyResId: Int): Boolean {
        return pref(pContext).contains(getKey(pContext, pKeyResId))
    }

    fun contains(pContext: Context?, pKey: String?): Boolean {
        return pref(pContext).contains(pKey)
    }

    internal fun getKey(pContext: Context, pResId: Int): String {
        return pContext.resources.getString(pResId)
    }

    internal fun editor(pContext: Context?): SharedPreferences.Editor {
        return pref(pContext).edit()
    }

    internal fun pref(pContext: Context?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(pContext)
    }
}