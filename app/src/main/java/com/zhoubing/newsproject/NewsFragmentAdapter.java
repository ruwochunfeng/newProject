package com.zhoubing.newsproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class NewsFragmentAdapter extends FragmentPagerAdapter {
    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }



    private static final String TAG="NewsFragmentAdapter";

    private String[] titles;
    private Context context;
    private List<Fragment> framents;

    public NewsFragmentAdapter(Context context,FragmentManager fm,List<Fragment> fragments,String[] titles) {
        super(fm);
        this.context = context ;
        this.titles = titles;
        this.framents = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return framents.get(position);
    }

    @Override
    public int getCount() {
        return framents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
