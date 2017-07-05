package com.zhoubing.newsproject;

import android.content.Context;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhoubing.newsproject.Constant.Constants;
import com.zhoubing.newsproject.adapter.NewsAdapeter;
import com.zhoubing.newsproject.bean.MeiziFuli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/6/26.
 */

public class NewsFragment extends Fragment {

    private static final int TAG = 1;

    private String message ;
    private RecyclerView recycler;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler;
    private static int mCurrent = 1 ;
    private View mView;
    private List<MeiziFuli.ResultsBean> result =new ArrayList<>();
//
//    public static Fragment newInstance(String message){
//        Bundle bundle = new Bundle();
//        bundle.putString(TAG,message);
//        NewsFragment newsFragment = new NewsFragment();
//        newsFragment.setArguments(bundle);
//        return  newsFragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrent = load();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case TAG:
                        MeiziFuli meiziFuli = (MeiziFuli) msg.obj;
                        result = meiziFuli.getResults();
                        initDatas();
                        break;
                    default:

                }
                super.handleMessage(msg);
            }
        };
        getAsync(Constants.fuliUrl+mCurrent);
    }

    private void initData() {
        progressBar = (ProgressBar) mView.findViewById(R.id.progressbar);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrent++;
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                getAsync(Constants.fuliUrl+mCurrent);

            }
        });
        recycler = (RecyclerView) mView.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void getAsync(String url){
        OkHttpClient mOkHttpClient=new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() == 200){
                    String shuju = response.body().string();
                    Gson gson = new Gson();
                    MeiziFuli meiziFuli = gson.fromJson(shuju,MeiziFuli.class);
                    Message message = new Message();
                    message.what = TAG;
                    message.obj = meiziFuli;
                    handler.sendMessage(message);
                }
            }
        });
    }

    private void initDatas() {
        if(result!=null&&result.size()>0) {
            recycler.setAdapter(new NewsAdapeter(getContext(), result));
            Log.i("测试一下","数据有");
            progressBar.setVisibility(View.GONE);
        }
        else{
            Log.i("测试一下","数据没有有");
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_info,container,false);
        }
        initData();
        return mView;
    }

    public void save(){
        FileOutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream  = getContext().openFileOutput("mCurrent", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(mCurrent);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

                try {
                    if(bufferedWriter != null)
                      bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public int load(){
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        int value =0;
        try {
            inputStream = getContext().openFileInput("mCurrent");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                value = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {

                try {
                    if(reader != null)
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return value;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        save();
    }
}
