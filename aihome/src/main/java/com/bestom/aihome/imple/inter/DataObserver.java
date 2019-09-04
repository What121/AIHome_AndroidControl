package com.bestom.aihome.imple.inter;

import com.bestom.aihome.imple.inter.Listener.DataReceivedListener;
import com.bestom.aihome.imple.inter.Obser.Observer;

/**
 * 观察者，接收串口数据 通知服务
 */
public class DataObserver implements Observer {

    private DataReceivedListener dataReceivedListener;


    public DataObserver(DataReceivedListener dataReceivedListener) {
        this.dataReceivedListener = dataReceivedListener;
    }


    @Override
    public void dataReceived(String dataHex) {
        dataReceivedListener.data(dataHex);
    }
}
