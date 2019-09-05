package com.bestom.aihome.WebSocket;

import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class AIWSClient extends WebSocketClient {
    private static final String TAG = "AIWSClient";

    private static URI serveruri;
    private int i=0;

    static {
        try {
//            wss://api.cn2.ilifesmart.com:8443/wsapp/
//            ws://121.40.165.18:8800
            serveruri = new URI("wss://api.cn2.ilifesmart.com:8443/wsapp/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static volatile AIWSClient instance;


    public static AIWSClient getInstance() {
        if (instance == null) {
            synchronized (AIWSClient.class) {
                if (instance == null) {
                    instance = new AIWSClient(serveruri);
                }
            }
        }
        return instance;
    }

    private AIWSClient(URI serverUri)  {
        super(serverUri);
    }

    private AIWSClient(URI serverUri, Draft protocolDraft)  {
        super(serverUri, protocolDraft);
    }

    private AIWSClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    private AIWSClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders)  {
        super(serverUri, protocolDraft, httpHeaders);
    }

    private AIWSClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout)  {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        //连接成功
        Log.i(TAG, "onOpen: ");
        i=0;
        //发送认证
        send("{\n" +
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
    }

    @Override
    public void onMessage(String s) {



        Log.i(TAG, "onMessage: "+s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

        Log.i(TAG, "onClose: "+s);
    }

    @Override
    public void onError(Exception e) {
        String message=e.getMessage();
        if (message.indexOf("Host is unresolved")==0){
            Log.i(TAG, "onError: "+message+"请检查网络连接状态和服务器"+serveruri+"状态！");
        }else {
            Log.i(TAG, "onError: "+message);
        }

        if (i<3){
            //尝试第i次重新连接，最大重连数3
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        i++;
                        Log.i(TAG, "reconnection: 10s 后 尝试第"+i+"次重新连接！！！");
                        Thread.sleep(10000);
                        AIWSClient.getInstance().reconnect();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();

        }

    }


}
