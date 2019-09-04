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

        Log.i(TAG, "onOpen: ");
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

        Log.i(TAG, "onError: "+e.getMessage());
    }


}
