package net.foofish.xiyuanfang.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.foofish.xiyuanfang.fragment.JavaFragment;
import net.foofish.xiyuanfang.fragment.OtherFragment;
import net.foofish.xiyuanfang.fragment.PythonFragment;

public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {

    String[] tabs = {"Python", "Java", "更多"};

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new PythonFragment();
            case 1:
                return new JavaFragment();
            case 2:
                return new OtherFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
