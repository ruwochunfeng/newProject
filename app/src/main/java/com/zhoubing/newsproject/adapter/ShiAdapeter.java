package com.zhoubing.newsproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.bean.BaiDeJieBean;
import com.zhoubing.newsproject.bean.ContentBean;
import com.zhoubing.newsproject.bean.NeiHanBean;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by dell on 2017/6/26.
 */

public class ShiAdapeter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private HashMap<String,String> mm = new HashMap<>();
    private List<NeiHanBean.DataBeanX.DataBean>  mList = new ArrayList<>();
    public ShiAdapeter(Context context, List<NeiHanBean.DataBeanX.DataBean> mlist) {
        this.context =context;
        mList = mlist;
        mediaMetadataRetriever = new MediaMetadataRetriever();
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if(viewType == 1 ) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else if(viewType == 5){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_view, parent, false);
            final AdViewHolder adViewHolder = new AdViewHolder(view);
            return adViewHolder;
        }else if(viewType == 8){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_view, parent, false);
            final InfoViewHolder infoViewHolder = new InfoViewHolder(view);
            return infoViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NeiHanBean.DataBeanX.DataBean resultsBean = mList.get(position);
        if(resultsBean.getType() == 1) {
            final  ShiAdapeter.ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.itemView.setTag(position);
            viewHolder.videoView.setUp(resultsBean.getGroup().getMp4_url(),JCVideoPlayer.SCREEN_LAYOUT_NORMAL,resultsBean.getGroup().getPublish_time(),resultsBean.getGroup().getCover_image_url());
            mediaMetadataRetriever.setDataSource(resultsBean.getGroup().getMp4_url(),mm);
            Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
            if(bitmap !=null) {
                viewHolder.videoView.thumbImageView.setImageBitmap(bitmap);
            }
            viewHolder.videoView.startButton.performClick();

         //  Glide.with(context).load(resultsBean.getGroup().getCover_image_url()).into(viewHolder.videoView.thumbImageView);
            viewHolder.textView.setText(resultsBean.getGroup().getContent());
        }else if(resultsBean.getType() == 5){
            ShiAdapeter.AdViewHolder adViewHolder = (AdViewHolder) holder;
            adViewHolder.itemView.setTag(position);
            Glide.with(context).load(resultsBean.getAd().getDisplay_image()).into(adViewHolder.imageView);
            adViewHolder.textView.setText(resultsBean.getAd().getDisplay_info());
        }else if(resultsBean.getType() == 8){
            ShiAdapeter.InfoViewHolder infoViewHolder = (InfoViewHolder) holder;
            infoViewHolder.itemView.setTag(position);
            infoViewHolder.textView.setText(resultsBean.getRoom().getText());
        }
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType() ;
    }



//


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
        JCVideoPlayerStandard videoView;
        public ViewHolder(View itemView) {
            super(itemView);
            videoView = (JCVideoPlayerStandard) itemView.findViewById(R.id.video);
            textView = (TextView) itemView.findViewById(R.id.video_text);
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
