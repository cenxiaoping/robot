package com.bojun.common.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 简  述
 * 作  者  lanxianzheng
 * 包  名  com.bojun.archives.adapter
 * 时  间  2020-07-06 16:13
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist;


    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mlist.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();//有几个页面
    }



}


