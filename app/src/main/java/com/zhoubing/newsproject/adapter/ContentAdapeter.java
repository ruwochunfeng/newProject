package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.ContentBean;
import com.zhoubing.newsproject.bean.zhihuApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class ContentAdapeter extends RecyclerView.Adapter<ContentAdapeter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ContentBean.ResultsBean> mList = new ArrayList<>();
    public ContentAdapeter(Context context, List<ContentBean.ResultsBean> mlist) {
        this.context =context;
        mList = mlist;
    }

    @Override
    public ContentAdapeter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item,parent,false);
        view.setOnClickListener(this);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentBean.ResultsBean resultsBean = mList.get(position);
        holder.itemView.setTag(position);
        if(resultsBean.getTitle() != null)
            holder.textView.setText(resultsBean.getTitle().trim());
        else {
            holder.textView.setText("有问题");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
       if(onItemClickListener != null){
           onItemClickListener.onItemClick(v, (int) v.getTag());
       }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_content);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
