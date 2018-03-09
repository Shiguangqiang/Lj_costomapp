package com.defence.costomapp.bean;

import java.io.Serializable;

/**
 * Created by Sgq
 * on 2018/3/5.
 *
 * 补货
 */


public class BuhuoInfoEntity implements Serializable {

    private int latticenumbers;
    private int iCount;
    private int savenum;
    private String showName;
    private String descVal;
    private int stocknumber;

    public void setLatticenumbers(int latticenumbers) {
        this.latticenumbers = latticenumbers;
    }

    public int getLatticenumbers() {
        return latticenumbers;
    }

    public void setICount(int iCount) {
        this.iCount = iCount;
    }

    public int getICount() {
        return iCount;
    }

    public void setSavenum(int savenum) {
        this.savenum = savenum;
    }

    public int getSavenum() {
        return savenum;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public void setDescVal(String descVal) {
        this.descVal = descVal;
    }

    public String getDescVal() {
        return descVal;
    }

    public void setStocknumber(int stocknumber) {
        this.stocknumber = stocknumber;
    }

    public int getStocknumber() {
        return stocknumber;
    }

}
