package com.defence.costomapp.bean;

import java.io.Serializable;

/**
 * Created by Sgq
 * on 2018/3/5.
 * <p>
 * 补货
 */


public class BuhuoInfoEntity implements Serializable {


    /**
     * iCount :
     * savenum :
     * showName : 国丰山楂无添加
     * stocknumber : 6
     * descVal : 蔬果山楂条
     * ku_cun : 4
     * latticenumbers : 37
     * gui_ge_id : 567
     */

    private String iCount;
    private String savenum;
    private String showName;
    private int stocknumber;
    private String descVal;
    private int ku_cun;
    private int latticenumbers;
    private int gui_ge_id;

    public String getICount() {
        return iCount;
    }

    public void setICount(String iCount) {
        this.iCount = iCount;
    }

    public String getSavenum() {
        return savenum;
    }

    public void setSavenum(String savenum) {
        this.savenum = savenum;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(int stocknumber) {
        this.stocknumber = stocknumber;
    }

    public String getDescVal() {
        return descVal;
    }

    public void setDescVal(String descVal) {
        this.descVal = descVal;
    }

    public String getKu_cun() {
        return String.valueOf(ku_cun);
    }

    public void setKu_cun(int ku_cun) {
        this.ku_cun = ku_cun;
    }

    public int getLatticenumbers() {
        return latticenumbers;
    }

    public void setLatticenumbers(int latticenumbers) {
        this.latticenumbers = latticenumbers;
    }

    public int getGui_ge_id() {
        return gui_ge_id;
    }

    public void setGui_ge_id(int gui_ge_id) {
        this.gui_ge_id = gui_ge_id;
    }
}
