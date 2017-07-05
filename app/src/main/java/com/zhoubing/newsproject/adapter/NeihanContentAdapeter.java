package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.ContentBean;
import com.zhoubing.newsproject.bean.NeiHanBean;
import com.zhoubing.newsproject.bean.NeihanContentBean;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by dell on 2017/6/26.
 */

public class NeihanContentAdapeter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<NeihanContentBean.DataBeanX.DataBean> mList = new ArrayList<>();
    public NeihanContentAdapeter(Context context, List<NeihanContentBean.DataBeanX.DataBean> mlist) {
        this.context =context;
        mList = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 1 ) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.neihan_content_item, parent, false);
            final NeihanContentAdapeter.ViewHolder viewHolder = new NeihanContentAdapeter.ViewHolder(view);
            return viewHolder;
        }else if(viewType == 5){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_view, parent, false);
            final NeihanContentAdapeter.AdViewHolder adViewHolder = new NeihanContentAdapeter.AdViewHolder(view);
            return adViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NeihanContentBean.DataBeanX.DataBean resultsBean = mList.get(position);
        if(resultsBean.getType() == 1) {
            final  NeihanContentAdapeter.ViewHolder viewHolder = (NeihanContentAdapeter.ViewHolder) holder;
            viewHolder.itemView.setTag(position);
            viewHolder.textView.setText(resultsBean.getGroup().getContent());
            viewHolder.textView1.setText(resultsBean.getGroup().getComment_count()+"");
            viewHolder.textView2.setText(resultsBean.getGroup().getDigg_count()+"");
        }else if(resultsBean.getType() == 5){
            NeihanContentAdapeter.AdViewHolder adViewHolder = (AdViewHolder) holder;
            adViewHolder.itemView.setTag(position);
            Glide.with(context).load(resultsBean.getAd().getDisplay_image()).into(adViewHolder.imageView);
            adViewHolder.textView.setText(resultsBean.getAd().getDisplay_info());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType() ;
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
        TextView textView1;
        TextView textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.neihan_content);
            textView1 = (TextView) itemView.findViewById(R.id.comment);
            textView2 = (TextView) itemView.findViewById(R.id.zan);
        }
    }

    public class AdViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView ;

        public AdViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_ad);
            textView = (TextView) itemView.findViewById(R.id.ad_text);
        }
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public InfoViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.info_text);
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
