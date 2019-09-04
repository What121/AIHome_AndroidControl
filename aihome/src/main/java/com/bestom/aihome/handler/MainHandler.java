package com.bestom.aihome.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class MainHandler extends Handler {
    private Activity mActivity;

    public MainHandler(Activity activity){
        this.mActivity=activity;
    }

    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){

            default:
                break;
        }
    }
}
