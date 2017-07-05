package com.zhoubing.newsproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.zhoubing.newsproject.bean.MeiziFuli;
import com.zhoubing.newsproject.ui.CameraFragment;
import com.zhoubing.newsproject.ui.ContentFragment;
import com.zhoubing.newsproject.ui.FunFragment;
import com.zhoubing.newsproject.ui.NeihanContentFragment;
import com.zhoubing.newsproject.ui.QianDuanFragment;
import com.zhoubing.newsproject.ui.ShipinFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments =new ArrayList<>();
    private String[] titles ={"头条","娱乐","成都","财经","科技","图片","汽车"};

    private NewsFragmentAdapter newsFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        initDatas();
    }


    private void initDatas() {

        fragments.add(new NewsFragment());
        fragments.add(new CameraFragment());
        fragments.add(new NeihanContentFragment());
        fragments.add(new ShipinFragment());
        fragments.add(new QianDuanFragment());
        fragments.add(new ContentFragment());
        fragments.add(new FunFragment());
        newsFragmentAdapter = new NewsFragmentAdapter(this,getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(newsFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
