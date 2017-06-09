package com.ray.projectframe.ui.view.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;

import com.ray.projectframe.R;


/**
 *  
 * 圆形ImageView，可设置最多两个宽度不同且颜色不同的圆形边框。 
 *  
 * 设置颜色在xml布局文件中由自定义属性配置参数指定 
 */  
  
public class RoundImageView extends android.support.v7.widget.AppCompatImageView {
  
    private int mBorderThickness = 0;  
  
    private Context mContext;
  
    private int defaultColor = 0xFFFFFFFF;  
  
    // 如果只有其中一个有值，则只画一个圆形边框  
  
    private int mBorderOutsideColor = 0;  
  
    private int mBorderInsideColor = 0;  
  
    // 控件默认长、宽  
  
    private int defaultWidth = 0;  
  
    private int defaultHeight = 0;  
  
    public RoundImageView(Context context) {
  
        super(context);  
  
        mContext = context;  
  
    }  
  
    public RoundImageView(Context context, AttributeSet attrs) {
  
        super(context, attrs);  
  
        mContext = context;  
  
        setCustomAttributes(attrs);  
  
    }  
  
    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
  
        super(context, attrs, defStyle);  
  
        mContext = context;  
  
        setCustomAttributes(attrs);  
  
    }  
  
    private void setCustomAttributes(AttributeSet attrs) {
  
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.roundedimageview);
  
        mBorderThickness = a.getDimensionPixelSize(  
                R.styleable.roundedimageview_border_thickness, 0);
  
        mBorderOutsideColor = a  
                .getColor(R.styleable.roundedimageview_border_outside_color,  
                        defaultColor);  
  
        mBorderInsideColor = a.getColor(  
                R.styleable.roundedimageview_border_inside_color, defaultColor);  
  
    }  
  
    @Override
    protected void onDraw(Canvas canvas) {
  
        Drawable drawable = getDrawable();
  
        if (drawable == null) {  
  
            return;  
  
        }  
  
        if (getWidth() == 0 || getHeight() == 0) {  
  
            return;  
  
        }  
  
        this.measure(0, 0);  
  
        if (drawable.getClass() == NinePatchDrawable.class)
  
            return;  
  
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
  
        Bitmap bitmap = b.copy(Config.ARGB_8888, true);
  
        if (defaultWidth == 0) {  
  
            defaultWidth = getWidth();  
  
        }  
  
        if (defaultHeight == 0) {  
  
            defaultHeight = getHeight();  
  
        }  
  
        int radius = 0;  
  
        if (mBorderInsideColor != defaultColor  
                && mBorderOutsideColor != defaultColor) {// 定义画两个边框，分别为外圆边框和内圆边框  
  
            radius = (defaultWidth < defaultHeight ? defaultWidth  
                    : defaultHeight) / 2 - 2 * mBorderThickness;  
  
            // 画内圆  
  
            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
                    mBorderInsideColor);  
  
            // 画外圆  
  
            drawCircleBorder(canvas, radius + mBorderThickness  
                    + mBorderThickness / 2, mBorderOutsideColor);  
  
        } else if (mBorderInsideColor != defaultColor  
                && mBorderOutsideColor == defaultColor) {// 定义画一个边框  
  
            radius = (defaultWidth < defaultHeight ? defaultWidth  
                    : defaultHeight) / 2 - mBorderThickness;  
  
            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
                    mBorderInsideColor);  
  
        } else if (mBorderInsideColor == defaultColor  
                && mBorderOutsideColor != defaultColor) {// 定义画一个边框  
  
            radius = (defaultWidth < defaultHeight ? defaultWidth  
                    : defaultHeight) / 2 - mBorderThickness;  
  
            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
                    mBorderOutsideColor);  
  
        } else {// 没有边框  
  
            radius = (defaultWidth < defaultHeight ? defaultWidth  
                    : defaultHeight) / 2;  
  
        }  
  
        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
  
        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight  
                / 2 - radius, null);  
  
    }  
  
    /** 
     *  
     * 获取裁剪后的圆形图片 
     *  
     * @param //radius半径
     */  
  
    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
  
        Bitmap scaledSrcBmp;
  
        int diameter = radius * 2;  
  
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片  
  
        int bmpWidth = bmp.getWidth();  
  
        int bmpHeight = bmp.getHeight();  
  
        int squareWidth = 0, squareHeight = 0;  
  
        int x = 0, y = 0;  
  
        Bitmap squareBitmap;
  
        if (bmpHeight > bmpWidth) {// 高大于宽  
  
            squareWidth = squareHeight = bmpWidth;  
  
            x = 0;  
  
            y = (bmpHeight - bmpWidth) / 2;  
  
            // 截取正方形图片  
  
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);  
  
        } else if (bmpHeight < bmpWidth) {// 宽大于高  
  
            squareWidth = squareHeight = bmpHeight;  
  
            x = (bmpWidth - bmpHeight) / 2;  
  
            y = 0;  
  
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);  
  
        } else {  
  
            squareBitmap = bmp;  
  
        }  
  
        if (squareBitmap.getWidth() != diameter  
                || squareBitmap.getHeight() != diameter) {  
  
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);  
  
        } else {  
  
            scaledSrcBmp = squareBitmap;  
  
        }  
  
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
  
        scaledSrcBmp.getHeight(),  
  
        Config.ARGB_8888);
  
        Canvas canvas = new Canvas(output);
  
        Paint paint = new Paint();
  
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());  
  
        paint.setAntiAlias(true);  
  
        paint.setFilterBitmap(true);  
  
        paint.setDither(true);  
  
        canvas.drawARGB(0, 0, 0, 0);  
  
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,  
  
        scaledSrcBmp.getHeight() / 2,  
  
        scaledSrcBmp.getWidth() / 2,  
  
        paint);  
  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
  
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);  
  
        bmp = null;  
  
        squareBitmap = null;  
  
        scaledSrcBmp = null;  
  
        return output;  
  
    }  
  
    /** 
     *  
     * 边缘画圆 
     */  
  
    private void drawCircleBorder(Canvas canvas, int radius, int color) {
  
        Paint paint = new Paint();
  
        /* 去锯齿 */  
  
        paint.setAntiAlias(true);  
  
        paint.setFilterBitmap(true);  
  
        paint.setDither(true);  
  
        paint.setColor(color);  
  
        /* 设置paint的　style　为STROKE：空心 */  
  
        paint.setStyle(Paint.Style.STROKE);
  
        /* 设置paint的外框宽度 */  
  
        paint.setStrokeWidth(mBorderThickness);  
  
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);  
  
    }  
  
}  
/*
定义自己的属性配置文件：attr.xml，放到values下：
[html] view plain copy print?
<?xml version="1.0" encoding="utf-8"?>  
<resources>  
  
    <declare-styleable name="roundedimageview">  
        <attr name="border_thickness" format="dimension" />  
        <attr name="border_inside_color" format="color" />  
        <attr name="border_outside_color" format="color"></attr>  
    </declare-styleable>  
  
</resources>  

在xml配置中使用控件：activity_main.xml
注意，需要在根节点下配置xmlns:imagecontrol="http://schemas.android.com/apk/res-auto" 
[html] view plain copy print?
<LinearLayout  
xmlns:android="http://schemas.android.com/apk/res/android"   
  
    xmlns:tools="http://schemas.android.com/tools"   
  
    xmlns:imagecontrol="http://schemas.android.com/apk/res-auto"   
  
    android:layout_width="fill_parent"   
  
    android:layout_height="fill_parent"   
  
    android:orientation="horizontal"   
  
    >   
  
     
  
    <!-- 没有指定圆形ImageView属性时，默认没有外边圆颜色 -->   
  
    <!-- 需要将图片资源自定为src ，或在程序中setImageResource(res) 不能设置background为图片，这样不能达到圆形效果-->   
  
    <com.dxd.roundimageview.RoundImageView    
  
        android:layout_width="100dp"   
  
        android:layout_height="100dp"   
  
        android:src="@drawable/img"   
  
        />   
  
    <!-- border_outside_color 外部圆圈的颜色 -->   
  
    <!-- border_inside_color 内部部圆圈的颜色 -->   
  
    <!-- border_thickness 外圆和内圆的宽度 -->   
  
    <com.dxd.roundimageview.RoundImageView    
  
        android:layout_width="100dp"   
  
        android:layout_height="100dp"   
  
        android:src="@drawable/img"   
  
        imagecontrol:border_inside_color="#bc0978"   
  
        imagecontrol:border_outside_color="#ba3456"   
  
        imagecontrol:border_thickness="1dp"   
  
        />
  
</LinearLayout>  */
