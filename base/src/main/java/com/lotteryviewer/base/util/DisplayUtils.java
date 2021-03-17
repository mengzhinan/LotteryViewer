package com.lotteryviewer.base.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.lotteryviewer.base.app.BaseApplication;

/**
 * @Author: duke
 * @DateTime: 2021-03-17 12:26:47
 * @Description: 功能描述：
 */
public class DisplayUtils {

    public void aa(Context context){
        Context ctx = context;
        if (ctx == null){
            ctx = BaseApplication.get();
        }
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getApplicationContext().getDisplay().getRealMetrics(dm);
    }

}
