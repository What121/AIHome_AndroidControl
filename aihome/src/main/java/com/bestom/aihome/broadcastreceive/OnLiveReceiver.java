package com.bestom.aihome.broadcastreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.bestom.aihome.R;

public class OnLiveReceiver extends BroadcastReceiver {
    private static final String TAG = "OnLiveReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        Resources resources = context.getResources();
        Log.i(TAG, "onReceive :action-- "+action);
        if (action.equals(resources.getString(R.string.broadcast_sys_boot))){
            Intent service=new Intent();
            service.setAction(resources.getString(R.string.intent_aihome_service_bootservice));
            service.setPackage(context.getPackageName());
            context.startService(service);
        }else if (action.equals(resources.getString(R.string.broadcast_aihome_service_reboot))){
            Intent service=new Intent();
            service.setAction(resources.getString(R.string.intent_aihome_service_bootservice));
            service.setPackage(context.getPackageName());
            context.startService(service);
        }else {
            ;
        }
    }





}
