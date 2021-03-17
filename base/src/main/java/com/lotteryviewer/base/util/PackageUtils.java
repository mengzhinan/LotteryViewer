package com.lotteryviewer.base.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:25:51
 * @Description: 功能描述：
 */
public class PackageUtils {
    /**
     * 根据 name 获得 MetaData 里值。
     * <p>
     * 比如：
     * <p>
     * <meta-data
     * android:name="LEANCLOUD_ID"
     * android:value="${LEANCLOUD_ID}"/>
     *
     * @param name meta-data 里的 name
     * @return 如果没有此值，那么返回 null。
     */
    @Nullable
    public static String getMetaData(@NonNull Context context, @NonNull String name) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object object = appInfo.metaData.get(name);
            String valueMeta = null;
            if (object != null) {
                valueMeta = String.valueOf(object);
            }
            return valueMeta;
        } catch (PackageManager.NameNotFoundException e) {
            // ignored
        }
        return null;
    }
}
