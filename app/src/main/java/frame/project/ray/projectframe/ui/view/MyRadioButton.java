package frame.project.ray.projectframe.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import frame.project.ray.projectframe.R;


public class MyRadioButton extends android.support.v7.widget.AppCompatRadioButton {
    //图片大小
    //private int drawableSize;

    public MyRadioButton(Context context) {
        this(context,null);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyRadioButton);
//        drawableSize = a.getDimensionPixelSize(R.styleable.MyRadioButton_rbDrawableTopSize, 50);
        Drawable drawableTop = a.getDrawable(R.styleable.MyRadioButton_rbDrawableTop);

        //释放资源
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if(top != null){	   //这里只要改后面两个参数就好了，一个宽一个是高，如果想知道为什么可以查找源码
            top.setBounds(0,0,60,50);
        }
        setCompoundDrawables(left,top,right,bottom);
    }
}