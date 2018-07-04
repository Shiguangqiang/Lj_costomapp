package com.defence.costomapp.bean;

/**
 * Created by lw on 2018/1/19.
 */

public class DataResponse<T> {
    private int sign;
    private String message;
    private T result;
    private String timelineN;

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTimelineN() {
        return timelineN == null ? "" : timelineN;
    }

    public void setTimelineN(String timelineN) {
        this.timelineN = timelineN;
    }
}
