package com.bestom.aihome.broadcastreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.bestom.aihome.R;
import com.bestom.aihome.WebSocket.AIWSClient;
import com.bestom.aihome.common.constant.Const;
import com.bestom.aihome.common.constant.NetworkType;
import com.bestom.aihome.common.utils.NetworkUtil;
import com.bestom.aihome.service.AIWSClientThreadHandler;

public class NetBroadReceiver extends BroadcastReceiver   {
    private static final String TAG = "NetBroadReceiver";
    private AIWSClientThreadHandler mAIWSClientThreadHandler;

    public static int mInt=0;

    public NetBroadReceiver() {
        mAIWSClientThreadHandler =AIWSClientThreadHandler.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        Resources resources = context.getResources();
        Log.i(TAG, "onReceive :action-- "+action);
        if(action.equals(resources.getString(R.string.broadcast_sys_net_change0))||action.equals(resources.getString(R.string.broadcast_sys_net_change1))){

               NetworkType networkType = NetworkUtil.getNetworkType(context);
               Log.i(TAG, "onReceive: 当前网络状态"+networkType+mInt);
               //网络状态在wifi的时候允许连接
               if (networkType==NetworkType.NETWORK_WIFI){
                   if (!AIWSClient.getInstance().isOpen()){
                       Log.i(TAG, "onReceive: now net type is wifi ,wsclient status is close, then reconnection wsServer.");

                       mAIWSClientThreadHandler.sendEmptyMessage(Const.AIHoneService_AIHoneServiceHandler_reconnectionWSClient);
//                       AIWSClient.getInstance().reconnect();
                   }else {
                       Log.i(TAG, "onReceive: wsclient status is open, dont need reconnect");
                   }
               }else {
                    AIWSClient.getInstance().i=0;
                   Log.i(TAG, "onReceive: AIWSClient 重连次数置0");
               }

        }else {
            ;
        }
    }


}
