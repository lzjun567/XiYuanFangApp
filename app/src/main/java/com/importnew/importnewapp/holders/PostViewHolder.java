package com.importnew.importnewapp.holders;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.importnew.importnewapp.R;
import com.importnew.importnewapp.WebViewActivity;
import com.importnew.importnewapp.models.Post;

/**
 * Created by lzjun on 6/21/15.
 */
public class PostViewHolder extends RecyclerView.ViewHolder {

    protected TextView title;
    protected TextView description;
    protected TextView time;
    protected TextView author;
    protected SimpleDraweeView image;
    protected View parent;

    public PostViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_post_title);
        description = (TextView) itemView.findViewById(R.id.item_post_description);
        image = (SimpleDraweeView) itemView.findViewById(R.id.item_post_img);
        time = (TextView) itemView.findViewById(R.id.item_post_date);
        author = (TextView) itemView.findViewById(R.id.item_post_user_name);
        parent = itemView;
    }

    public void onBindViewHolder(Post post){
        parent.setTag(post);
        title.setText(post.getTitle());
        description.setText(post.getDescription());
        image.setImageURI(Uri.parse(post.getCover()));
        time.setText(post.getFormatDate());
        author.setText(post.getUser());

    }

    public static PostViewHolder create(final Context context, ViewGroup parent){
        View v = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = (Post)v.getTag();
                if (post!=null){
                    WebViewActivity.actionStart(context, post.getId());
                }
            }
        });
        return new PostViewHolder(v);

    }



}
