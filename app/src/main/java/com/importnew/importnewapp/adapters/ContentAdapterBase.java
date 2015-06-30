package com.importnew.importnewapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.importnew.importnewapp.ApplicationController;
import com.importnew.importnewapp.DataController;
import com.importnew.importnewapp.R;
import com.importnew.importnewapp.holders.FooterViewHolder;
import com.importnew.importnewapp.models.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    protected abstract void onBindCustomViewHolder(RecyclerView.ViewHolder hodler, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CommonFeature.FOOTER.ordinal()) {
            mLoadingMoreView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer, parent, false);
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
