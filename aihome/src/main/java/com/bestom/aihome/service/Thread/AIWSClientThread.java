package com.bestom.aihome.service.Thread;

import android.os.Handler;
import android.os.Looper;

import com.bestom.aihome.common.constant.Const;
import com.bestom.aihome.service.AIWSClientThreadHandler;

public class AIWSClientThread extends Thread {
    private Handler mAIWSClientThreadHandler;


    @Override
    public void run() {
        Looper.prepare();
        mAIWSClientThreadHandler=AIWSClientThreadHandler.getInstance();
        try {
            sleep(5*1000);
            mAIWSClientThreadHandler.sendEmptyMessage(Const.AIHoneService_AIHoneServiceHandler_connectionWSClient);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Looper.loop();
    }
}
