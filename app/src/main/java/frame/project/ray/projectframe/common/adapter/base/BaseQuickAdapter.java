package frame.project.ray.projectframe.common.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/9/6.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context context;

    protected int layoutResId;

    protected final List<T> mData;

    private OnItemClickListener mOnItemClickListener = null;

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;


    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    protected BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization mData.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param mData        A new list is created out of this one to avoid mutable list
     */
    protected BaseQuickAdapter(Context context, int layoutResId, List<T> mData) {
        this.mData = mData == null ? new ArrayList<T>() : mData;
        this.context = context;
        this.layoutResId = layoutResId;
    }

    protected BaseQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this(context, multiItemTypeSupport, null);
    }

    protected BaseQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> mData) {
        this.context = context;
        this.mData = mData == null ? new ArrayList<T>() : new ArrayList<T>(mData);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public ArrayList<T> getData(){
        return (ArrayList<T>) mData;
    }


    public T getItem(int position) {
        if (position >= mData.size()) return null;
        return mData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null) {
            return mMultiItemTypeSupport.getItemViewType(position, getItem(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        if (mMultiItemTypeSupport != null) {
            int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
            view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);

        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        }
        /**
         *  ziti
         */


        view.setOnClickListener(this);
        BaseAdapterHelper vh = new BaseAdapterHelper(context,view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper, int position) {
        helper.itemView.setTag(position);
        T item = getItem(position);
        convert((H) helper, item, position);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item,int position);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void add(T elem) {
        mData.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(mData.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        mData.set(index, elem);
        notifyDataSetChanged();
    }

    public void setAll(List<T> elem) {
        mData.clear();
        if(elem!=null){
            mData.addAll(elem);
        }
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mData.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mData.clear();
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return mData.contains(elem);
    }

    /**
     * Clear mData list
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

}
