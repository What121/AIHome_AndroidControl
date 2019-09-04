package com.bestom.aihome.imple.inter.Listener;

/**
 * 接收到数据监听
 */
public interface DataReceivedListener<T> {
    void data(T data);
}
