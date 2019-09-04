package com.bestom.aihome.imple.inter.Listener;

public interface RespSampleListener<T> {

    void onSuccess(int code, T t);

    void onFailure(int code, String errMsg);
}
