package com.defence.costomapp.bean;

import java.io.Serializable;

/**
 * Created by Sgq
 * on 2018/3/5.
 */


public class BuhuoMessageEntity implements Serializable {
    private String id;
    private String machinename;
    private String lastReportTime;
    private int notRepairCount;
    private String machinenumber;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setLastReportTime(String lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public String getLastReportTime() {
        return lastReportTime;
    }

    public void setNotRepairCount(int notRepairCount) {
        this.notRepairCount = notRepairCount;
    }

    public int getNotRepairCount() {
        return notRepairCount;
    }

    public void setMachinenumber(String machinenumber) {
        this.machinenumber = machinenumber;
    }

    public String getMachinenumber() {
        return machinenumber;
    }

}
