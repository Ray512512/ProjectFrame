package com.ray.library.view.view.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

import com.ray.library.R;


/***
 * 可以倾斜的textview
 */
public class LeanTextView extends android.support.v7.widget.AppCompatTextView {
  public int getmDegrees() {
    return mDegrees;
  }
 
  public void setmDegrees(int mDegrees) {
    this.mDegrees = mDegrees;
    invalidate();
  }
 
  private int mDegrees;
 
  public LeanTextView(Context context) {
    super(context, null);
  }
 
  public LeanTextView(Context context, AttributeSet attrs) {
    super(context, attrs, android.R.attr.textViewStyle);
    this.setGravity(Gravity.CENTER);
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeanTextView);
    mDegrees = a.getDimensionPixelSize(R.styleable.LeanTextView_degree, 0);
    a.recycle();
  }
 
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
  }
 
  @Override
  protected void onDraw(Canvas canvas) {
    canvas.save();
    canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
    canvas.rotate(mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
    super.onDraw(canvas);
    canvas.restore();
  }
}