package com.bestom.aihome.service.Thread;

import android.util.Log;

import com.bestom.aihome.WebSocket.AIWSClient;

public class AIWSThread extends Thread {
    private static final String TAG = "AIWSThread";

    public boolean flag=true;

    @Override
    public void run() {
        while (flag){
            try {
                sleep(1000);
                Log.i(TAG, "run: States"+AIWSClient.getInstance().getReadyState().toString());

                if (AIWSClient.getInstance().getReadyState().toString().equals("OPEN")) {
                    AIWSClient.getInstance().send("{\n" +
                            "\"id\": 957,\n" +
                            "\"method\": \"WbAuth\",\n" +
                            "\"system\": {\n" +
                            "\"ver\": \"1.0\",\n" +
                            "\"lang\": \"en\",\n" +
                            "\"userid\": \"1111111\",\n" +
                            "\"appkey\": \"APPKEY_XXXXXXXX\",\n" +
                            "\"time\": 1447641115,\n" +
                            "\"sign\": \"SIGN_XXXXXXXX\"\n" +
                            "}\n" +
                            "}");

                    Log.i(TAG, "run: 发送认证 ");
                    flag=false;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
