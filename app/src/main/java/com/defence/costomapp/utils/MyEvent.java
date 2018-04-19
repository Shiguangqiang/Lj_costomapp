package com.defence.costomapp.utils;

/**
 * Created by author Sgq
 * on 2018/4/17.
 */


public class MyEvent {
    public int eventType;//可能类型有很多种，数据也不一样
    public Object data;//数据对象
    public String device;
    public String status;

    public String getDevice() {
        return device == null ? "" : device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyEvent(int eventType, String datebefore, String dateafter, String device, String status) {
        this.eventType = eventType;
        this.datebefore = datebefore;
        this.dateafter = dateafter;
        this.device = device;
        this.status = status;
    }

    public String datebefore;

    public String getDatebefore() {
        return datebefore == null ? "" : datebefore;
    }

    public void setDatebefore(String datebefore) {
        this.datebefore = datebefore;
    }

    public String getDateafter() {
        return dateafter == null ? "" : dateafter;
    }

    public void setDateafter(String dateafter) {
        this.dateafter = dateafter;
    }

    public String dateafter;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
