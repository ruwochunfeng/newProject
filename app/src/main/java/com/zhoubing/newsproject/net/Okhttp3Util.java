package com.zhoubing.newsproject.net;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhoubing.newsproject.bean.MeiziFuli;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dell on 2017/6/26.
 */

public class Okhttp3Util {

    public static void getAsync(String url){
        OkHttpClient mOkHttpClient=new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if(response!=null){
                  String shuju = response.body().string();
                  Gson gson = new Gson();
                  MeiziFuli meiziFuli = gson.fromJson(shuju,MeiziFuli.class);
              }

            }
        });
    }
    private void postAsynHttp(String url) {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i("wangshu", str);


            }

        });
    }
}
