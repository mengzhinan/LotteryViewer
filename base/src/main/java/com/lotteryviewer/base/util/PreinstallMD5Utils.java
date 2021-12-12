package com.lotteryviewer.base.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @Author: duke
 * @DateTime: 2021-12-10 17:02:09
 * @Description: 功能描述：
 */
public class PreinstallMD5Utils {

    public static String md5(String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return "";
        }
        str = str.trim();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                int newInt = (b & 0xFF);
                String temp = Integer.toHexString(newInt).toUpperCase(Locale.getDefault());
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
