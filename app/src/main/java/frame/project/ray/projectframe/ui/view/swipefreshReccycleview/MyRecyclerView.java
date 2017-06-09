package frame.project.ray.projectframe.ui.view.swipefreshReccycleview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.traffic.wifiapp.ui.view.baseadapter.BaseQuickAdapter;
import com.traffic.wifiapp.utils.L;


/**
 * Created by wxy on 16/7/27.
 */
public class MyRecyclerView extends RecyclerView {
    protected LoadMoreListener mLoadingListener;
    protected boolean isLoadingData = false;
    protected boolean isnomore = false;
    protected boolean loadingMoreEnabled = false;
    protected SwipeRefreshLayout refreshLayout;
    protected LoadingMoreFooter moreFooter;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        moreFooter = new LoadingMoreFooter(context);
        moreFooter.setState(LoadingMoreFooter.STATE_COMPLETE);
    }
    public void setClickLookMoreLinstener(LoadingMoreFooter.ClickLookMoreLinstener clickLookMoreLinstener) {
        moreFooter.setClickLookMoreLinstener(clickLookMoreLinstener);
    }

    public void initLayout(SwipeRefreshLayout refreshLayout, BaseQuickAdapter quickAdapter) {
        this.refreshLayout = refreshLayout;
        if (moreFooter.getParent()!=null){
            ViewGroup viewGroup = (ViewGroup) moreFooter.getParent();
            viewGroup.removeView(moreFooter);
        }
        quickAdapter.addFooterView(moreFooter);
    }

    //是否在刷新中
    public boolean getIsRefreshing() {
        if (refreshLayout != null) {
            return refreshLayout.isRefreshing();
        } else {
            return false;
        }
    }

    // 加载更多回调
    public void setLoadingListener(LoadMoreListener mLoadingListener) {
        this.mLoadingListener = mLoadingListener;
    }

    //打开加载更多
    public void openMoreEnabled(boolean hasMore) {
        this.loadingMoreEnabled = hasMore;
        moreFooter.setNomore(false);
        loadMoreComplete();
        setIsnomore(!hasMore);
    }
    //打开加载更多
    public void openMoreEnabled(boolean hasMore, boolean clickMore) {
        this.loadingMoreEnabled = hasMore;
        moreFooter.setNomore(clickMore);
        loadMoreComplete();
        setIsnomore(!hasMore);
    }

    public void loadMoreComplete() {
        isLoadingData = false;
        if (refreshLayout != null){
            refreshLayout.setRefreshing(false);
        }
        moreFooter.setState(LoadingMoreFooter.STATE_COMPLETE);
    }


    public void setIsnomore(boolean isnomore) {
        this.isnomore = isnomore;
        if (isnomore) {
            moreFooter.setState(LoadingMoreFooter.STATE_NOMORE);
        } else {
            moreFooter.setState(LoadingMoreFooter.STATE_COMPLETE);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE && mLoadingListener != null && !isLoadingData && loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            L.v("是否加载更多",layoutManager.getChildCount()+"/"+lastVisibleItemPosition+"/"+layoutManager.getItemCount()+"/"+
                    layoutManager.getItemCount()+"/"+layoutManager.getChildCount()+"/"+!isnomore+"/" +!getIsRefreshing());
            if (layoutManager.getChildCount() > 0 && lastVisibleItemPosition >= layoutManager.getItemCount() - 2
                    && layoutManager.getItemCount() >= layoutManager.getChildCount()
                    && !isnomore &&
                    !getIsRefreshing()) {
                isLoadingData = true;
                moreFooter.setState(LoadingMoreFooter.STATE_LOADING);
                mLoadingListener.onLoadMore();
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
