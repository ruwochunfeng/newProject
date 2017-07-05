package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.ContentBean;
import com.zhoubing.newsproject.bean.QianDuanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/26.
 */

public class QianDuanAdapter extends RecyclerView.Adapter<QianDuanAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<QianDuanBean.ResultsBean> mList = new ArrayList<>();
    public QianDuanAdapter(Context context, List<QianDuanBean.ResultsBean> mlist) {
        this.context =context;
        mList = mlist;
    }

    @Override
    public QianDuanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qianduan_item,parent,false);
        view.setOnClickListener(this);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QianDuanBean.ResultsBean resultsBean = mList.get(position);
        holder.itemView.setTag(position);
        if(resultsBean.getDesc() != null)
            holder.textView.setText(resultsBean.getDesc().trim());
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
            textView = (TextView) itemView.findViewById(R.id.text_qianduan);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
