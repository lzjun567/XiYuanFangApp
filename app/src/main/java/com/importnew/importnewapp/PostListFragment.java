package com.importnew.importnewapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.importnew.importnewapp.adapters.PostListAdapter;
import com.importnew.importnewapp.models.Post;

import java.util.List;

/**
 * Created by lzjun on 6/20/15.
 */
public class PostListFragment extends Fragment implements UIRespondent, SwipeRefreshLayout.OnRefreshListener {

    protected ContentLoadingProgressBar mLoadingBar;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ViewGroup rootView;
    protected PostListAdapter mAdapter;
    protected DataController dataController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.post_list_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                dataController.loadMore(currentPage);
            }

            ;
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_post_list);
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLoadingBar = (ContentLoadingProgressBar) view.findViewById(R.id.loading_bar);
        rootView = (ViewGroup) view;
        return rootView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dataController = new DataController();
        dataController.addUIRespondent(this);
        mAdapter = new PostListAdapter(getActivity(), dataController);
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onRefresh() {
        dataController.refresh();

    }

    @Override
    public void onInitializeDone(List<Post> data) {
        dataController.addAll(data);
        mLoadingBar.hide();
        mAdapter.notifyDataSetChanged();
        rootView.bringChildToFront(mSwipeRefreshLayout);

    }

    @Override
    public void onLoadMoreDone(List<Post> data) {
        dataController.addAll(data);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRefreshDone(List<Post> data) {

        for (int i = data.size() - 1; i >= 0; i--) {
            dataController.add(0, data.get(i));
        }
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
