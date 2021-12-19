package com.lotteryviewer.base.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @ Author: duke
 * @ DateTime: 2021-12-07 16:14:00
 * @ Description: 功能描述： 支持特殊裁切的 ImageView
 */
public class CropImageView extends AppCompatImageView {

    public CropImageView(@NonNull Context context) {
        super(context);
    }

    public CropImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CropImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置 scaleType：使左右上平铺，底部多余的裁掉
     */
    public void postCropBottom() {
        post(() -> {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            Bitmap bitmap = drawableToBitmap(drawable);
            bitmap = cropBottom(bitmap);
            if (bitmap == null) {
                return;
            }
            setImageBitmap(bitmap);
            invalidate();
        });
    }

    /**
     * 图片宽度拉伸到 ImageView 宽度
     * 图片顶部与 ImageView 顶部对齐，底部超出 ImageView 的多余部分裁掉
     *
     * @param bitmap 原始图片
     * @return new bitmap
     */
    private Bitmap cropBottom(Bitmap bitmap) {

        int ivWidth = getMeasuredWidth();
        int ivHeight = getMeasuredHeight();

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        float scaleWidth = ((float) ivWidth) / bitmapWidth;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);

        // 裁掉 bitmap 的底部多余部分
        int targetHeight = Math.min((int) (ivHeight / scaleWidth), bitmapHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, targetHeight, matrix, true);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, drawableWidth, drawableHeight);
        Bitmap.Config config = Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(drawableWidth, drawableHeight, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }

}
