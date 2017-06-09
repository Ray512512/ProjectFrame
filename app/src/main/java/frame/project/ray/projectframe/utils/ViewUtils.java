package frame.project.ray.projectframe.utils;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Ray on 2017/5/18.
 * email：1452011874@qq.com
 * sign:每当你在感叹，如果有这样一个东西就好了的时候，请注意，其实这是你的机会. —— 郭霖
 */

public class ViewUtils {

    public static Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }


    /**
     * 获取视图宽度
     * */
    public static int getViewWidth(View view){
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width,height);
        width = view.getMeasuredWidth();
        return width;
    }
    /**
     * 获取视图高度
     * */
    public static int getViewHeight(View view){
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width,height);
        height=view.getMeasuredHeight();
        return height;
    }
}
