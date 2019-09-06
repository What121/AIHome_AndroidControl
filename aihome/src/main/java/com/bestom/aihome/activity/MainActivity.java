package com.bestom.aihome.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bestom.aihome.R;
import com.bestom.aihome.common.utils.MD5;
import com.bestom.aihome.handler.MainHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainHandler mMainHandler;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=findViewById(R.id.show_tv);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent service =new Intent();
        service.setAction("com.bestom.aihome.service.AIHomeService");
        service.setPackage(this.getPackageName());
//        bindService(service,mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String sign0= MD5.MD5("method:WbAuth,time:1567789848,userid:7752721,usertoken:K9aJxmxwo8jYl9zOcdbuCA,appkey:Bh8ea4bYaeMDzyALNaOlBQ,apptoken:zZoKJr0dJh2ohmou4G1ozQ");
        String sign1= MD5.stringToMD5("method:WbAuth,time:1567789848,userid:7752721,usertoken:K9aJxmxwo8jYl9zOcdbuCA,appkey:Bh8ea4bYaeMDzyALNaOlBQ,apptoken:zZoKJr0dJh2ohmou4G1ozQ");

        mTextView.setText("MD5---:"+sign0);
        mTextView.append("\nstringToMD5---:"+sign1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
