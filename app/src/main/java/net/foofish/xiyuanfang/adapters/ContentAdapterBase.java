package net.foofish.xiyuanfang.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.foofish.xiyuanfang.DataController;
import net.foofish.xiyuanfang.holders.FooterViewHolder;

/**
 * Created by lzjun on 6/21/15.
 */
public abstract class ContentAdapterBase<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected View mLoadingMoreView;
    protected Context mContext;
    protected DataController mDataController;


    public ContentAdapterBase(Context context, final DataController dataController) {
        this.mContext = context;
        this.mDataController = dataController;

    }

    public enum CommonFeature {
        HEADER, COMMON, FOOTER
    }

    protected abstract RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType);

    protected abstract void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CommonFeature.FOOTER.ordinal()) {
            mLoadingMoreView = LayoutInflater.from(parent.getContext()).inflate(net.foofish.xiyuanfang.R.layout.recyclerview_footer, parent, false);
            return new FooterViewHolder(mLoadingMoreView);

        } else {
            return onCreateCustomContentHolder(parent, viewType);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footViewHolder = (FooterViewHolder) holder;
            footViewHolder.onBindViewHolder();

        } else {
            onBindCustomViewHolder(holder, position);
        }

    }


    @Override
    public int getItemCount() {
        return mDataController.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (position == getItemCount() - 1) {
            return CommonFeature.FOOTER.ordinal();
        }
        return CommonFeature.COMMON.ordinal();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mDataController.initial();
    }
}
