package com.bestom.aihome.manager.serial;

import android.os.Message;
import android.util.Log;

import com.bestom.aihome.common.constant.Const;
import com.bestom.aihome.common.exception.*;
import com.bestom.aihome.common.utils.DataTurn;
import com.bestom.aihome.handler.SerialManagerHandler;

import java.io.IOException;
import java.security.InvalidParameterException;

import static com.bestom.aihome.common.constant.Const.SerialManager_SerialManagerHandler_what;

/**
 * 串口管理类
 */
public class SerialManager extends SerialHelper {
    private static final String TAG = "Camera Serial";
    private static volatile SerialManager instance;

    private String sPort = "/dev/ttyS1";
    private int iBaudRate = 115200;
    //private int iBaudRate = 9600;

    private DataTurn DataTurn =new DataTurn();

    private String rData = "";
    private StringBuilder dataBuilder;

    private boolean _iSerialflag=false;
    private CheckDataThread mCheckDataThread;
    private SerialManagerHandler mSerialManagerHandler;

    private SerialManager() {
        mSerialManagerHandler = new SerialManagerHandler();
        dataBuilder = new StringBuilder();
        config(sPort,iBaudRate);
    }

    public static SerialManager getInstance() {
        if (instance == null) {
            synchronized (SerialManager.class) {
                if (instance == null) {
                    instance = new SerialManager();
                }
            }
        }
        return instance;
    }

    /**********************************************************************
     * 配置串口
     * @param port 串口号
     * @param iBaud 波特率
     */
    public void config(String port, int iBaud) {
        this.sPort = port;
        this.iBaudRate = iBaud;
        if (isOpen()) {
            setPort(sPort);
            setBaudRate(iBaudRate);
        }
    }

    /**********************************************************************
     * 打开串口
     */
    public void turnOn() {
        try {
            open();

//            setPort(sPort);
//            setBaudRate(iBaudRate);
        } catch (SecurityException e) {
            Log.e(TAG, "turnOn: 打开串口失败:没有串口读/写权限!",e);
        } catch (IOException e) {
            Log.e(TAG, "turnOn: 打开串口失败:未知错误!",e );
        } catch (InvalidParameterException e) {
            Log.e(TAG, "turnOn: 打开串口失败:参数错误!",e );
        }
    }

    /**********************************************************************
     * 关闭串口
     */
    public void turnOff() {
        //stopSend();
        _iSerialflag=false;
        close();
    }

    /**********************************************************************
     * 向串口发送16进制字符串数据
     * @param sHex 16进制的指令字符串
     */
    public void sendHex(String sHex) throws SerialException {
        if (!isOpen()) {
            throw new SerialException("请打开串口，再发送数据！");
        }
        super.sendHex(sHex);
    }

    private class CheckDataThread extends Thread {
        @Override
        public void run() {
//            super.run();
            int index;
            while (_iSerialflag){
                index= dataBuilder.indexOf(Const.MAGIC);
                    if ((index == 0)&&(dataBuilder.length() >= 64)) {
//                    Log.i("read:",">=32了，进行数据准确判断");
                        Log.i(TAG, "run: readcheck datasize==64,receive");
                            String body_size_Hex = dataBuilder.substring(15, 16);
//                        Log.i(TAG, "run: body_size_Hex"+body_size_Hex);
                        int body_size = new  DataTurn().HexToInt(body_size_Hex);
//                        Log.i(TAG, "run: body_size"+body_size);
                         String data =dataBuilder.substring(0, 64);
//                        Log.i("read:","标准数据包通过发送更新"+data);
                        Message msg = new Message();
                        msg.what = SerialManager_SerialManagerHandler_what;
                        msg.obj =data;
                        mSerialManagerHandler.sendMessage(msg);

                        dataBuilder.delete(0, 64);
                    }else if ((dataBuilder.length()>0)&&(index>0)){
                        Log.e(TAG, "run:ERROR-Camera DATA HEX(index="+index+"):" + dataBuilder );
                        Log.e(TAG, "run: index："+ dataBuilder.indexOf(Const.MAGIC));
                        dataBuilder.delete(0,index);
                    }
//                    else if ((dataBuilder.length()>0)&&(index==-1)){
//                        FLogs.e(TAG, "ERROR-Camera DATA HEX（clear）：" + dataBuilder);
//                        dataBuilder.setLength(0); //清空
//                    }
            }
        }
    }

    @Override
    protected void onDataReceived(byte[] buffer, int size) {
        dataBuilder.append(DataTurn.ByteArrToHex(buffer));
        if (mCheckDataThread==null){
            _iSerialflag=true;
            mCheckDataThread=new CheckDataThread();
            mCheckDataThread.start();
        }
    }

}
