package com.ray.library.view.view.swipefreshReccycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ray.library.R;
import com.ray.library.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wxy on 16/7/27.
 */
public class LoadingMoreFooter extends LinearLayout {
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    @BindView(R2.id.loading_text)
    TextView loadingText;
    @BindView(R2.id.loading_progress)
    ProgressBar progressBar;
    private Context mContext;
    private ClickLookMoreLinstener clickLookMoreLinstener;
    private boolean canClickMore = false;
    public LoadingMoreFooter(Context context) {
        super(context);
        initView(context);
    }
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setClickLookMoreLinstener(ClickLookMoreLinstener clickLookMoreLinstener) {
        this.clickLookMoreLinstener = clickLookMoreLinstener;
    }


    private void initView(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.def_loading, this);
        ButterKnife.bind(this, view);
        this.setVisibility(View.GONE);
    }

    public void setNomore(boolean canClickMore){
        this.canClickMore = canClickMore;
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                loadingText.setText(mContext.getText(R.string.listview_loading));
                progressBar.setVisibility(View.VISIBLE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                loadingText.setText(mContext.getText(R.string.listview_loading));
                progressBar.setVisibility(View.VISIBLE);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                if (canClickMore){
                    loadingText.setText("点击查看更多");
                }else{
                    loadingText.setText("已无更多");
                }
                progressBar.setVisibility(View.GONE);
                this.setVisibility(View.GONE);
                break;
        }

    }

    @OnClick(R2.id.loading_view)
    public void onClick() {
        if (clickLookMoreLinstener != null && canClickMore){
            clickLookMoreLinstener.onMoreClick();
        }
    }

    public interface ClickLookMoreLinstener{
        void onMoreClick();
    }
}
