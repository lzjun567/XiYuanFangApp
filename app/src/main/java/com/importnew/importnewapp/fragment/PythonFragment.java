package com.importnew.importnewapp.fragment;

import android.os.Bundle;
import android.view.View;

import com.importnew.importnewapp.DataController;
import com.importnew.importnewapp.adapters.PostListAdapter;

/**
 * Created by lzjun on 8/16/15.
 */
public class PythonFragment extends PostListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        dataController = new DataController("python");
        super.onViewCreated(view, savedInstanceState);

    }
}
