package com.bestom.aihome.imple.inter;

import android.util.Log;

import com.bestom.aihome.imple.inter.Obser.Observable;
import com.bestom.aihome.imple.inter.Obser.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，数据中转、通知服务
 */
public class DataObservable implements Observable {
    private static final String TAG = "LocalDataObservable";

    //关键字volatile:用以声明变量的值可能随时会别的线程修改，
    // 使用volatile修饰的变量会强制将修改的值立即写入主存，
    // 主存中值的更新会使缓存中的值失效(非volatile变量不具备这样的特性，
    // 非volatile变量的值会被缓存，线程A更新了这个值，
    // 线程B读取这个变量的值时可能读到的并不是是线程A更新后的值)。
    // volatile会禁止指令重排。
    private static volatile DataObservable instance;

    private List<Observer> observerList;
    public String dataHex;

    private DataObservable() {
        observerList=new ArrayList<Observer>();
    }

    public static DataObservable getInstance(){
        if (instance==null){
            //关键字synchronized:一个线程访问一个对象中的synchronized(this)同步代码块时，
            // 其他试图访问该对象的线程将被阻塞
            //该代码块只会同步执行,不会异步在不同线程
            synchronized (DataObservable.class){
                if (instance==null){
                    instance=new DataObservable();
                }
            }
        }
        return instance;
    }

    /**
     *注册观察者
     * @param o 观察者
     */
    @Override
    public void registerObserver(Observer o) {
        Log.d(TAG, "registerObserver: 注册消息接收");
        observerList.add(o);
    }

    /**
     * 移除观察者
     * @param o
     */
    @Override
    public void removeObserver(Observer o) {
        if (!observerList.isEmpty()){
            Log.d(TAG, "removeObserver: 移除消息接收");
            observerList.remove(o);
        }
    }

    /**
     * 通知观察者更新
     */
    @Override
    public void notifyObserver() {
        //Log.i("**更新信息**","被观察者数："+observerList.size()+"--"+dataHex);
        for (int i=0;i<observerList.size();i++){
            //Log.i("**被观察者**",i+""+observerList.get(i).toString());
            Observer observer=observerList.get(i);
            if (observer!=null)
                /*分发更新（根据不同的cmd类型 ）
                    01  设备上传命令
                    04  回复命令
                    02  系统下发命令
                    03  事件命令
                */
                observer.dataReceived(dataHex);
        }
    }

    /**
     * 观察者更新的新信息
     * @param sHex  16进制字符串
     */

     public void setInfo(String sHex){
//         Log.i("更新:","第二步 observer 更新");
        this.dataHex=sHex;
        notifyObserver();
    }
}
