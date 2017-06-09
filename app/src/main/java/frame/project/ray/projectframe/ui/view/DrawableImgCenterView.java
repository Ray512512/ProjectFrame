package frame.project.ray.projectframe.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by ray on 2017/1/3.
 */

public class DrawableImgCenterView extends android.support.v7.widget.AppCompatButton {

    public DrawableImgCenterView(Context context) {
        super(context);
    }

    public DrawableImgCenterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableImgCenterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                final float textWidth = getPaint().measureText(getText().toString());
                final int drawablePadding = getCompoundDrawablePadding();
                final int drawableWidth = drawableLeft.getIntrinsicWidth();
                final float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
