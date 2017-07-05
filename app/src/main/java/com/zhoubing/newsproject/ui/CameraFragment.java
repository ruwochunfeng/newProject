package com.zhoubing.newsproject.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhoubing.newsproject.Constant.Constants;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.activity.CameraActivity;
import com.zhoubing.newsproject.activity.WebShow;
import com.zhoubing.newsproject.adapter.ContentAdapeter;
import com.zhoubing.newsproject.bean.BaiDeJieBean;
import com.zhoubing.newsproject.bean.ContentBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/6/28.
 */

public class CameraFragment extends Fragment {
    private View mView;
    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.camera_fragment, null, false);
            initData();
        }
       return  mView;
    }
    private void initData(){
        mButton = (Button) mView.findViewById(R.id.button_to_camera);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
    }

}
