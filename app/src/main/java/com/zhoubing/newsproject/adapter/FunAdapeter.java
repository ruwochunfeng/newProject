package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.MeiziFuli;
import com.zhoubing.newsproject.bean.zhihuApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class FunAdapeter extends RecyclerView.Adapter<FunAdapeter.ViewHolder> {

    private Context context;
    private List<zhihuApi.StoriesBean> mList = new ArrayList<>();
    public FunAdapeter(Context context, List<zhihuApi.StoriesBean> mlist) {
        this.context =context;
        mList = mlist;
    }

    @Override
    public FunAdapeter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fun_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        zhihuApi.StoriesBean resultsBean = mList.get(position);
        if(resultsBean.getTitle() != null)
            holder.textView.setText(resultsBean.getTitle());
        else {
            holder.textView.setText("有问题");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_fun);
        }
    }

}
