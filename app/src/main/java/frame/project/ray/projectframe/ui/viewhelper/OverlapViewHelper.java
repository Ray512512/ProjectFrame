package frame.project.ray.projectframe.ui.viewhelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * 作者： corer   时间： 2015/10/18.
 * 功能：切换布局，用一个新的View覆盖原先的View
 * 修改：
 */
public class OverlapViewHelper implements ICaseViewHelper {

    public ICaseViewHelper mHelper;
    public View mDataView;

    public OverlapViewHelper(View view) {
        this.mDataView = view;

        /*找到父View*/
        ViewGroup parent ;
        if (view.getParent()!=null){
            parent = (ViewGroup) view.getParent();
        }else {
            parent=(ViewGroup)view.getRootView().findViewById(android.R.id.content);
        }


        /*记录要显示的View在父View中的位置*/
        int childIndex=0;
        int childCount= parent.getChildCount();
        for (int index=0;index<childCount;index++){
            if (view== parent.getChildAt(index)){
                childIndex =index;
                break;
            }
        }

        /*重新将一个frameLayout添加进原来的View的位子中*/
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout frameLayout = new FrameLayout(view.getContext());
        parent.removeViewAt(childIndex);
        parent.addView(frameLayout,childIndex, layoutParams);

        /*在这个frameLayout中实现将新的View覆盖在原来的view上*/
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View floatView = new View(view.getContext());
        frameLayout.addView(view, params);
        frameLayout.addView(floatView, params);
        mHelper = new ReplaceViewHelper(floatView);
    }

    @Override
    public View getCurrentView() {
        return mHelper.getCurrentView();
    }

    @Override
    public void restoreLayout() {
        mHelper.restoreLayout();
    }

    @Override
    public void showCaseLayout(View view) {
        mHelper.showCaseLayout(view);
    }

    @Override
    public void showCaseLayout(int layoutId) {
        showCaseLayout(inflate(layoutId));
    }

    @Override
    public View inflate(int layoutId) {
        return mHelper.inflate(layoutId);
    }

    @Override
    public Context getContext() {
        return mHelper.getContext();
    }

    @Override
    public View getDataView() {
        return mDataView;
    }
}
