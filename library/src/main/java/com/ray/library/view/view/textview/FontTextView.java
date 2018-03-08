package com.ray.library.view.view.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaoniao on 2016/3/14.
 */
public class FontTextView extends TextView {

    public static Typeface tf;
    public static Typeface tf_bold;

    public FontTextView(Context context) {
        super(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 字体
     */
    public void init(Context context){


            if (tf == null) {
                tf = Typeface.createFromAsset(context.getAssets(),
                        "font/SIMYOU.TTF");
            }
        setTypeface(tf);
    }


    @Override
    public void setTypeface(Typeface tf1, int style) {
        if (style == Typeface.BOLD){
            if (tf_bold == null){
                tf_bold = Typeface.createFromAsset(getContext().getAssets(),
                        "font/pingfangbold.ttf");
            }
            super.setTypeface(tf_bold);
            }else {
            if (tf == null) {
                tf = Typeface.createFromAsset(getContext().getAssets(),
                        "font/pingfangregular.ttf");
            }
            super.setTypeface(tf);
        }
    }
}
