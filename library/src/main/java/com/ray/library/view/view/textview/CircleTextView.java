package com.ray.library.view.view.textview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author YanLu
 * @since 16/6/9
 */

public class CircleTextView extends TextView {

	public CircleTextView(Context context) {
		super(context);
	}

	public CircleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = (int) (getTextWidth(getPaint(), getText().toString()) + 0.5);
        int height = (int) (getTextHeight(getPaint(), getText().toString()) + 0.5);
        int viewLength = Math.max(width, height) + 12;
        setMeasuredDimension(viewLength, viewLength);

	}


    public static float getTextWidth(Paint paint, String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text + "", 0, text.length(), rect);
        return rect.width();
    }


    public static float getTextHeight(Paint paint, String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text + "", 0, text.length(), rect);
        return rect.height();
    }

}

