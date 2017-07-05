package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.MeiziFuli;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class NewsAdapeter extends RecyclerView.Adapter<NewsAdapeter.ViewHolder> {

    private Context context;
    private List<MeiziFuli.ResultsBean> mList =new ArrayList<>();
    public NewsAdapeter(Context context, List<MeiziFuli.ResultsBean> mlist) {
        this.context =context;
        mList = mlist;
    }

    @Override
    public NewsAdapeter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerpicture,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MeiziFuli.ResultsBean resultsBean = mList.get(position);
        Glide.with(context).load(resultsBean.getUrl()).into(holder.imageView);
        holder.textView.setText(resultsBean.getDesc());

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
