package net.foofish.xiyuanfang.fragment;

import android.os.Bundle;
import android.view.View;

import net.foofish.xiyuanfang.DataController;

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
