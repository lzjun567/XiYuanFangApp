package com.importnew.importnewapp.holders;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.importnew.importnewapp.R;

/**
 * Created by lzjun on 6/21/15.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {
    public final ContentLoadingProgressBar mProgressBar;
    public final TextView mTextView;
    public FooterViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (ContentLoadingProgressBar)itemView.findViewById(R.id.loading_more_progress);
        mTextView = (TextView)itemView.findViewById(R.id.loading_more_tips);
    }

    public void onBindViewHolder() {
//        mTextView.setText("嘿嘿，没有内容可加载了");
//        mTextView.setVisibility(View.VISIBLE);
//        mProgressBar.hide();
    }
}
