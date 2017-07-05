package com.zhoubing.newsproject.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;


/**
 * Created by dell on 2017/7/4.
 */

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private Context context;
    private Camera mCamera;

    public CameraSurface(Context context,Camera camera) {
        this(context,null,0);
        mCamera =camera;

    }

    public CameraSurface(Context context, AttributeSet attrs) {
        this(context,attrs, 0 );
    }

    public CameraSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
         startPreview();
    }

    private void startPreview() {
        if(mCamera !=null){
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void stopPreview(){
        if(mCamera !=null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
          if(holder.getSurface() ==null){
              return;
          }

         mCamera.stopPreview();

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopPreview();
    }




}
