package com.bestom.aihome.imple.inter.Listener;

public interface RespListener {

    void onSuccess(int code, String msg);

    void onFailure(int code, String errMsg);
}
