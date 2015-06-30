package com.importnew.importnewapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

import com.importnew.importnewapp.DataController;
import com.importnew.importnewapp.holders.PostViewHolder;
import com.importnew.importnewapp.models.Post;

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
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder hodler, int position) {

        ((PostViewHolder)hodler).onBindViewHolder(mDataController.get(position));

    }
}