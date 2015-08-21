package net.foofish.xiyuanfang.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.foofish.xiyuanfang.DataController;
import net.foofish.xiyuanfang.holders.PostViewHolder;
import net.foofish.xiyuanfang.models.Post;

/**
 * Created by lzjun on 6/21/15.
 */
public class PostListAdapter extends ContentAdapterBase<Post> {

    private Context mContext;

    public PostListAdapter(Context context, DataController controller) {
        super(context, controller);
        this.mContext = context;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType) {
        return PostViewHolder.create(mContext, parent);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((PostViewHolder) holder).onBindViewHolder(mDataController.get(position));

    }
}