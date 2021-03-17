package com.lotteryviewer.base.util;

import android.net.Uri;

import androidx.core.content.FileProvider;

import com.lotteryviewer.base.BaseApplication;

import java.io.File;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:49:33
 * @Description: 功能描述：
 */
public class FileProviderUtil {

    private static String FILES_PROVIDER_AUTHORITIES = BaseApplication.get().getPackageName();

    public static String getAuthorities() {
        return FILES_PROVIDER_AUTHORITIES;
    }

    public static Uri getUriForFile(File file) {
        return FileProvider.getUriForFile(BaseApplication.get(), FileProviderUtil.getAuthorities(), file);
    }
}
