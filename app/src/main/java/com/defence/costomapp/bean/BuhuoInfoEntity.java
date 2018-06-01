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

    public String getGui_ge_id() {
        return gui_ge_id == null ? "" : gui_ge_id;
    }

    public void setGui_ge_id(String gui_ge_id) {
        this.gui_ge_id = gui_ge_id;
    }

    private String gui_ge_id; // 商品规格ID编号



    public String getKu_cun() {
        return ku_cun == null ? "" : ku_cun;
    }

    public void setKu_cun(String ku_cun) {
        this.ku_cun = ku_cun;
    }

    private String ku_cun;


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
