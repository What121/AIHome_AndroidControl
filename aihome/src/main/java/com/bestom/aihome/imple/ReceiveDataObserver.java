package com.bestom.aihome.imple;

import com.bestom.aihome.imple.inter.Listener.DataReceivedListener;
import com.bestom.aihome.imple.inter.Obser.Observer;

/**
 * 观察者，接收串口数据 通知服务
 */
public class ReceiveDataObserver implements Observer {

    private DataReceivedListener dataReceivedListener;

    public ReceiveDataObserver(DataReceivedListener dataReceivedListener) {
        this.dataReceivedListener=dataReceivedListener;
    }

    public void setDataReceivedListener(DataReceivedListener dataReceivedListener) {
        this.dataReceivedListener = dataReceivedListener;
    }

    @Override
    public void dataReceived(String dataHex) {



    }
}
