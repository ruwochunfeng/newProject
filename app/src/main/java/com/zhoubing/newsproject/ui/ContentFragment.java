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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhoubing.newsproject.Constant.Constants;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.activity.WebShow;
import com.zhoubing.newsproject.adapter.ContentAdapeter;
import com.zhoubing.newsproject.adapter.FunAdapeter;
import com.zhoubing.newsproject.bean.ContentBean;
import com.zhoubing.newsproject.bean.zhihuApi;

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

public class ContentFragment extends Fragment {

    private final static int TAG = 3;
    private View mView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static  int  theme = 1;
    private Handler handler;
    private List<ContentBean.ResultsBean> result = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case TAG:
                        ContentBean  contentBean= (ContentBean) msg.obj;
                        result = contentBean.getResults();
                        initDatas();
                        break;
                    default:
                        break;

                }
                super.handleMessage(msg);
            }
        };
        getAsync(Constants.contentApi+theme);

    }

    private void initDatas() {
        if(result!=null&&result.size()>0) {
            ContentAdapeter contentAdapeter =new ContentAdapeter(getContext(),result);
            contentAdapeter.setOnItemClickListener(new ContentAdapeter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    Toast.makeText(getContext(),"点击的位置为："+position,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),WebShow.class);
                    intent.putExtra("geshi",0);
                    intent.putExtra("shuju",result.get(position).getContent());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(contentAdapeter);
            Log.i("测试一下","数据有");
            Log.i("测试一下",result.get(0).getTitle());
            progressBar.setVisibility(View.GONE);
        }
        else{
            Log.i("测试一下","数据没有有");
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void initData(){
        recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, Color.GRAY,2));
        progressBar = (ProgressBar) mView.findViewById(R.id.progressbar_content);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_layout_content);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                theme++;
                swipeRefreshLayout.setRefreshing(false);
                getAsync(Constants.contentApi+theme);
            }
        });
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
                    ContentBean contentBean = gson.fromJson(shuju,ContentBean.class);
                    Message message = new Message();
                    message.what = TAG;
                    message.obj = contentBean;
                    handler.sendMessage(message);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.content_fragment,container,false);
            initData();
        }
        return mView;
    }
}
