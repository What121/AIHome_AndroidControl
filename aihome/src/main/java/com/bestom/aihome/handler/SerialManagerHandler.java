package com.bestom.aihome.handler;

import android.os.Handler;
import android.os.Message;

import com.bestom.aihome.R;
import com.bestom.aihome.imple.inter.DataObservable;

import static com.bestom.aihome.common.constant.Const.SerialManager_SerialManagerHandler_what;

public class SerialManagerHandler extends Handler {

    public SerialManagerHandler() {

    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case SerialManager_SerialManagerHandler_what:
                // 设置到被观察者,通知更新
                DataObservable.getInstance().setInfo(String.valueOf(msg.obj));
                break;
            default:
                break;
        }


    }


}
