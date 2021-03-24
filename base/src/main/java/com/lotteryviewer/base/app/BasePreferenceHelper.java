package com.lotteryviewer.base.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lotteryviewer.base.util.TextUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;


/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:19:32
 * @Description: 功能描述：
 */
public class BasePreferenceHelper {

    /**
     * isFirstTime 的一个通用方法
     * 对于同一个 key 只会为 true 一次
     */
    public static boolean isFirstTime(Context context, int key) {
        if (getBoolean(context, key, true)) {
            putBoolean(context, key, false);
            return true;
        }
        return false;
    }

    //<editor-fold desc="Preference 的一些基本操作">
    public static void putInt(Context pContext, int pKeyResId, int pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putInt(getKey(pContext, pKeyResId), pValue).apply();
    }

    public static int getInt(Context pContext, int pKeyResId, int pDefValue) {
        return pContext == null ? pDefValue : pref(pContext).getInt(getKey(pContext, pKeyResId), pDefValue);
    }

    public static void putLong(Context pContext, int pKeyResId, long pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putLong(getKey(pContext, pKeyResId), pValue).apply();
    }

    public static long getLong(Context pContext, int pKeyResId, long pDefValue) {
        return pContext == null ? pDefValue : pref(pContext).getLong(getKey(pContext, pKeyResId), pDefValue);
    }

    public static void putBoolean(Context pContext, int pKeyResId, boolean pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putBoolean(getKey(pContext, pKeyResId), pValue).apply();
    }

    public static boolean getBoolean(Context pContext, int pKeyResId, boolean pDefValue) {
        return pContext == null ? pDefValue : pref(pContext).getBoolean(pContext.getResources().getString(pKeyResId),
                pDefValue);
    }

    public static void putFloat(Context pContext, int pKeyResId, float pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putFloat(getKey(pContext, pKeyResId), pValue).apply();
    }

    public static float getFloat(Context pContext, int pKeyResId, float pDefValue) {
        return pContext == null ? pDefValue : pref(pContext).getFloat(pContext.getResources().getString(pKeyResId),
                pDefValue);
    }

    public static void putString(Context pContext, int pKeyResId, String pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putString(getKey(pContext, pKeyResId), pValue).apply();
    }

    public static void putString(Context pContext, String pKey, String pValue) {
        if (pContext == null) {
            return;
        }
        editor(pContext).putString(pKey, pValue).apply();
    }

    public static String getString(Context context, int keyResId, String defValue) {
        return context == null ? defValue : pref(context).getString(context.getResources().getString(keyResId),
                defValue);
    }

    public static String getString(Context pContext, String pKey, String pDefValue) {
        return pref(pContext).getString(pKey, pDefValue);
    }

    public static void putTime(Context pContext, int pKeyResId, GregorianCalendar pTime,
                               DateFormat dateFormat) {
        if (pContext == null) {
            return;
        }
        String str = dateFormat.format(pTime.getTime());
        editor(pContext).putString(getKey(pContext, pKeyResId), str).apply();
    }

    public static GregorianCalendar getTime(Context pContext, int pKeyResId, String pDefValueStr,
                                            DateFormat dateFormat) {
        String str = getString(pContext, pKeyResId, pDefValueStr);
        if (!TextUtil.isEmpty(str)) {
            try {
                GregorianCalendar time = new GregorianCalendar();
                time.setTime(dateFormat.parse(str));
                return time;
            } catch (ParseException pE) {
                pE.printStackTrace();
            }
        }
        return null;
    }

    public static void remove(Context pContext, int pKeyResId) {
        editor(pContext).remove(getKey(pContext, pKeyResId)).apply();
    }

    public static void remove(Context pContext, String pKey) {
        if (contains(pContext, pKey)) {
            editor(pContext).remove(pKey).apply();
        }
    }

    public static boolean contains(Context pContext, int pKeyResId) {
        return pref(pContext).contains(getKey(pContext, pKeyResId));
    }

    public static boolean contains(Context pContext, String pKey) {
        return pref(pContext).contains(pKey);
    }

    protected static String getKey(Context pContext, int pResId) {
        return pContext.getResources().getString(pResId);
    }

    protected static SharedPreferences.Editor editor(Context pContext) {
        return pref(pContext).edit();
    }

    protected static SharedPreferences pref(Context pContext) {
        return PreferenceManager.getDefaultSharedPreferences(pContext);
    }

}
