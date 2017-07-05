package com.zhoubing.newsproject.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zhoubing.newsproject.R;
import com.zhoubing.newsproject.camera.CameraSurface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CameraActivity extends AppCompatActivity {

    private CameraSurface cameraSurface ;
    private Camera camera;
    private Button button;
    private Camera.PictureCallback mPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.camera_open);
        button = (Button) findViewById(R.id.button_capture);
        if(checkCamera(getApplicationContext())){
            camera = openCamera(1);
        }else{
            camera = null;
            Log.i("cameraActivity","摄像头没有啊，很郁闷的啊");
        }
        cameraSurface = new CameraSurface(getApplicationContext(),camera);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.camera_preview);
        frameLayout.addView(cameraSurface);
        mPicture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Format format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String name = format.format(new Date());
                File file = new File(MediaStore.Images.ImageColumns.DATA+name);

                if(!file.exists()){
                    if(!file.mkdirs()){

                    }
                }
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camera.takePicture(null,null,mPicture);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }
        });
    }
    private Camera openCamera(int cameraID){
        checkCamera(getApplicationContext());


        Camera camera = null;
        camera = Camera.open(cameraID);
        return camera;

    }
    private boolean checkCamera(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            return true;
        else {
            return false;
        }
    }

    private void startPreview() {
        if(camera !=null){
            try {
                camera.setPreviewDisplay(cameraSurface.getHolder());
                camera.setDisplayOrientation(90);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void stopPreview(){
        if(camera !=null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}
