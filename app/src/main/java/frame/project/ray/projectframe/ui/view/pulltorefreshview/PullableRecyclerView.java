package frame.project.ray.projectframe.ui.view.pulltorefreshview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PullableRecyclerView extends RecyclerView implements Pullable
{

	public PullableRecyclerView(Context context)
	{
		super(context);
	}

	public PullableRecyclerView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableRecyclerView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	private boolean canpullup = true,canpulldown=true;
	public void setCanPullUp(boolean b) {
		canpullup = b;
	}
	public void setCanPullDown(boolean b) {
		canpulldown = b;
	}


	@Override
	public boolean canPullDown()
	{
		if (getAdapter().getItemCount() == 0)
		{
			// 没有item的时候也可以下拉刷新
			return canpulldown;
		} else if (((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition() == 0
				&& getChildAt(0).getTop() >= 0)
		{
			// 滑到ListView的顶部了
			return canpulldown;
		} else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (getAdapter().getItemCount() == 0)
		{
			// 没有item的时候也可以上拉加载
			return canpullup;
		} else if (((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition() == (getAdapter().getItemCount() - 1))
		{
			// 滑到底部了
			if (getChildAt(((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition() - ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition()) != null
					&& getChildAt(
				((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition()
									- ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition()).getBottom() <= getMeasuredHeight())
				return canpullup;
		}
		return false;
	}
}
