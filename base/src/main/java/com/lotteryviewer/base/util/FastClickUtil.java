package com.lotteryviewer.base.util;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:46:23
 * @Description: 功能描述：
 */
public class FastClickUtil {


    private static long lastClickTime;

    public static boolean isFastClick() {
        return isFastClick(800);
    }

    public static boolean isFastClick(long timeThreshold) {
        long time = System.currentTimeMillis();
        long timeDelta = time - lastClickTime;
        lastClickTime = time;
        return timeDelta <= timeThreshold;
    }
}
