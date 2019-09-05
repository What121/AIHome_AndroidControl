package com.bestom.aihome.WebSocket.Thread;

import android.util.Log;

import com.bestom.aihome.WebSocket.AIWSClient;

public class AIWSThread extends Thread {
    private static final String TAG = "AIWSThread";

    //关键字volatile:用以声明变量的值可能随时会别的线程修改，
    // 使用volatile修饰的变量会强制将修改的值立即写入主存，
    // 主存中值的更新会使缓存中的值失效(非volatile变量不具备这样的特性，
    // 非volatile变量的值会被缓存，线程A更新了这个值，
    // 线程B读取这个变量的值时可能读到的并不是是线程A更新后的值)。
    // volatile会禁止指令重排。
    public static volatile boolean flag=true;

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
                }
                flag=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
