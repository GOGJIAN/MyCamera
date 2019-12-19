package com.shimao.mycamerademo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.huantansheng.easyphotos.ui.CropActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textview).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

//                EasyPhotos.createCamera(MainActivity.this).start(new SelectCallback() {
//                    @Override
//                    public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
//
//                    }
//                },EasyCameraActivity.CAMERA_TYPE);
                startActivity(new Intent(MainActivity.this, CropActivity.class));
            }
        });
    }
}
