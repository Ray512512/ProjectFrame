package frame.project.ray.projectframe.base.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import frame.project.ray.projectframe.retrofit.ApiManager;
import frame.project.ray.projectframe.retrofit.ApiService;

/**
 * Created by xy on 16/5/11.
 */
public abstract class BaseFragment extends Fragment{
    protected Context mContext;


    protected View rootView;// 根视图
    protected Resources mRes;
    /**
     * 需要初始化Presenter重写
     **/
    protected abstract int inflateContentView();
    protected abstract void initView(Bundle savedInstanceSate);

    //用来保存当前界面的状态和恢复上一个界面的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflateContentView() > 0) {
            rootView = inflater.inflate(inflateContentView(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ButterKnife.bind(this, rootView);
            mContext = getActivity();
            mRes = getResources();

            initView(savedInstanceState);
            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setContentView(View view) {
        rootView = view;
    }

    public View getContentView() {
        return rootView;
    }

}
