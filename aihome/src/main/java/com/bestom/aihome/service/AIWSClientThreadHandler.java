package com.bestom.aihome.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.bestom.aihome.WebSocket.AIWSClient;

import static com.bestom.aihome.common.constant.Const.AIHoneService_AIHoneServiceHandler_connectionWSClient;
import static com.bestom.aihome.common.constant.Const.AIHoneService_AIHoneServiceHandler_reconnectionWSClient;

public  class AIWSClientThreadHandler extends Handler {
    private AIWSClient mAIWSClient;

    private static volatile AIWSClientThreadHandler instance;

    private static final String TAG = "AIWSClientThreadHandler";



    private AIWSClientThreadHandler() {
        mAIWSClient=AIWSClient.getInstance();
    }

    public static AIWSClientThreadHandler getInstance() {
        if (instance == null) {
            synchronized (AIWSClientThreadHandler.class) {
                if (instance == null) {
                    instance = new AIWSClientThreadHandler();
                }
            }
        }
        return instance;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case AIHoneService_AIHoneServiceHandler_reconnectionWSClient:
                mAIWSClient.reconnect();
                Log.i(TAG, "handleMessage:  reconnect");
                break;
            case AIHoneService_AIHoneServiceHandler_connectionWSClient:
                mAIWSClient.connect();
                Log.i(TAG, "handleMessage:  connect");
                break;
            default:
                break;
        }


    }


}
