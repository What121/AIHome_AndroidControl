package com.bestom.aihome.bean;

public class NetBean {

    private String APPKEY="Bh8ea4bYaeMDzyALNaOlBQ";
    private String APPTOKEN="zZoKJr0dJh2ohmou4G1ozQ";
    private String USERID="7752721";
    private String USERTOKEN="f15oJ3oJMQgaOwIrOznmZw";
    private int TIME;

    public String getAPPKEY() {
        return APPKEY;
    }

    public String getAPPTOKEN() {
        return APPTOKEN;
    }

    public String getUSERID() {
        return USERID;
    }

    public String getUSERTOKEN() {
        return USERTOKEN;
    }

    public int getTIME() {
        return TIME;
    }

    public void setTIME(int TIME) {
        this.TIME = TIME;
    }

    public String getSignString(){

        return "method:WbAuth,time:"+getTIME()+",userid:"+getUSERID()+",usertoken:"+getUSERTOKEN()+ ",appkey:"+getAPPKEY()+",apptoken:"+getAPPTOKEN();

    }


}
