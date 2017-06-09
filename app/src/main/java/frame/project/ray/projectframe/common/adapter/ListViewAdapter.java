package frame.project.ray.projectframe.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import frame.project.ray.projectframe.common.adapter.base.ViewHolder;

public abstract class ListViewAdapter<T> extends BaseAdapter{
	
	public ArrayList<T> mDatas = new ArrayList<>();
	protected Context mContext;
	protected final int mItemLayoutId;
	public ListViewAdapter(Context context, ArrayList<T> mDatas, int mItemLayoutId){
		this.mContext = context;
		this.mDatas.addAll(mDatas);
		this.mItemLayoutId = mItemLayoutId;

	}


	public ArrayList<T> getDatas(){
		return  mDatas;
	}
	/**
	 *  添加数据
	 */
	public void addmDatas(ArrayList<T> datas){
		if (datas != null){
			mDatas.addAll(datas);
			notifyDataSetChanged();
		}
	}
	/**
	 *  设置数据
	 */

	public void setmDatas(ArrayList<T> datas){
		if (datas != null){
			mDatas.clear();
			mDatas.addAll(datas);
			notifyDataSetChanged();
		}
	}
	public void setmData(int position,T data){
		if (data != null){
			mDatas.set(position,data);
			notifyDataSetChanged();
		}
	}
	/**
	 * 清空列表数据
	 */
	public void clearDatas(){
		mDatas.clear();
		notifyDataSetChanged();
	}

	/**
	 * 添加单一数据
 	 * @return
	 */
	public void addSingle(T data){
		if (data != null){
			mDatas.add(data);
			notifyDataSetChanged();
		}
	}
	public void addSingle(int p,T data){
		if (data != null){
			mDatas.add(p,data);
			notifyDataSetChanged();
		}
	}

	/**
	 * 删除一个单一数据
	 * @return
	 */
	public void deleteSingle(T data){
		if(data==null)return;
		mDatas.remove(data);
		notifyDataSetChanged();
	}



	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder = getViewHolder(position, convertView, parent);
		convert(holder, getItem(position),position);
		return holder.getConvertView();
	}
	
	public abstract void convert(ViewHolder helper, T item,int position);
	  
    private ViewHolder getViewHolder(int position, View convertView,  
            ViewGroup parent)  
    {  
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);  
    }


}
