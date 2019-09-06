package com.bestom.aihome.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.bestom.aihome.WebSocket.AIWSClient;
import com.bestom.aihome.imple.EnventDataObserver;
import com.bestom.aihome.imple.ReceiveDataObserver;
import com.bestom.aihome.imple.inter.DataObservable;
import com.bestom.aihome.imple.inter.DataObserver;
import com.bestom.aihome.imple.inter.Listener.DataReceivedListener;
import com.bestom.aihome.manager.serial.SerialManager;
import com.bestom.aihome.service.Thread.AIWSClientThread;


public class AIHomeService extends Service {
    private static final String TAG = "AIHomeService";
    private AIWSClientThreadHandler mAIWSClientThreadHandler;

    static {
        AIWSClient.getInstance();
        System.loadLibrary("serial_port");
    }

    public AIHomeService()  {
        Log.d(TAG, "AIHomeService: ....");

        //打开串口
        SerialManager.getInstance().turnOn();

        //连接websocketClient
        new AIWSClientThread().start();


    }

    @Override
    public void onCreate() {
        super.onCreate();

        //注册所有消息返回DataObserver对象
        DataObservable.getInstance().registerObserver(new DataObserver(new DataReceivedListener() {
            @Override
            public void data(Object data) {
                String hexdata = (String)data;

                if (AIWSClient.getInstance().getReadyState().toString().equals("OPEN")){
//                    AIWSClient.getInstance().send(hexdata);
                    //上传至 服务器的 hexdata
//                    Log.i(TAG, "data: 上传至 服务器");

                    Log.i(TAG, "data: 下发到apk");
                }else {
//                    AIWSThread.flag =true;

                    Log.i(TAG, "data: 与服务器断开连接");
//                    AIWSClient.getInstance().connect();
//                    Log.i(TAG, "run: reconection WSocket ");
                }

            }
        }));

        //注册设备上报消息返回ReceiveDataObserver对象
        DataObservable.getInstance().registerObserver(new ReceiveDataObserver(new DataReceivedListener() {
            @Override
            public void data(Object data) {

            }
        }));

        //注册事件上报消息返回EnventDataObserver对象
        DataObservable.getInstance().registerObserver(new EnventDataObserver(new DataReceivedListener() {
            @Override
            public void data(Object data) {

            }
        }));


//        1.没有配设备是读不到数据的+
//                aihomeBUILDER.builder .open().read();
//        2.配置设备后，可以读数据
//        aihomebuilder.builder.open().read();

        Log.d(TAG, "onCreate: ....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ....");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: ....");


//        绑定replay 回复 信息；

        Binder binder=new Binder();

        return binder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ....");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ....");
        sendBroadcast(new Intent("com.bestom.aihome.broadcast.AIHomeService_REBOOT"));
        super.onDestroy();
    }



}
