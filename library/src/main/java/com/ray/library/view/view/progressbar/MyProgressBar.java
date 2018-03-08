package com.ray.library.view.view.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/10/24.
 */
    public class MyProgressBar extends ProgressBar {
    private Context mContext;
    
    private String text;
    
    private String text1;
    
    private Paint mPaint;
    
    public MyProgressBar(Context context)
    {
        super(context);
        this.mContext = context;
        initText();
    }
    
    public MyProgressBar(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initText();
    }
    
    public MyProgressBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initText();
    }
    
    @Override
    public synchronized void setProgress(int progress)
    {
        setText(progress);
        super.setProgress(progress);
        
    }
    
    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(text, 0, text.length(), rect);
        this.mPaint.getTextBounds(text1, 0, text1.length(), rect);
        
        int progress = getProgress();
        int width = getWidth();
        
        if (getMax() != 0)
        {
            int start = width / getMax() * progress;
            int end = width / getMax() * (getMax() - progress);
            int x_two = ((end / 2) + start) - rect.centerX();
            int x_one = (start / 2) - rect.centerX();
            int y = (getHeight() / 2) - rect.centerY();
            if (getProgress() == 0)
            {
                canvas.drawText(this.text1, x_two, y, this.mPaint);
            }
            else
            {
                canvas.drawText(this.text, x_one, y, this.mPaint);
                canvas.drawText(this.text1, x_two, y, this.mPaint);
            }
        }
    }
    
    // 初始化，画笔
    private void initText()
    {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int mScreenWidth = dm.widthPixels;
        int mScreenHeight = dm.heightPixels;
        float ratioWidth = (float) mScreenWidth / 720;
        float ratioHeight = (float) mScreenHeight / 1080;
        float ratioMetrics = Math.min(ratioWidth, ratioHeight);
        int textSize = Math.round(16 * ratioMetrics);
        
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setTextSize(textSize);
    }
    
    // 设置文字内容
    private void setText(int progress)
    {
        this.text = String.valueOf(progress);
        this.text1 = String.valueOf(getMax() - progress);
    }
}
