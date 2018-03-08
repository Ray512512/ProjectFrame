package com.ray.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * 将bitmap处理为圆形 
 * @author Stanny 
 * 
 * 2015年9月28日 
 */  
public class BitmapUtil {
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();   
        int height = bitmap.getHeight();   
        float roundPx;   
        float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;   
        if (width <= height) {   
        roundPx = width / 2;   
        top = 0;   
        bottom = width;   
        left = 0;   
        right = width;   
        height = width;   
        dst_left = 0;   
        dst_top = 0;   
        dst_right = width;   
        dst_bottom = width;   
        } else {   
        roundPx = height / 2;   
        float clip = (width - height) / 2;   
        left = clip;   
        right = width - clip;   
        top = 0;   
        bottom = height;   
        width = height;   
        dst_left = 0;   
        dst_top = 0;   
        dst_right = height;   
        dst_bottom = height;   
        }   
        Bitmap output = Bitmap.createBitmap(width,
        height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;   
        final Paint paint = new Paint();
        final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
        final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);   
        canvas.drawARGB(0, 0, 0, 0);   
        paint.setColor(color);   
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);   
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);   
        return output;   
        }

    // drawable 转换成bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();// 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);// 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 把drawable内容画到画布中
        return bitmap;
    }
}  