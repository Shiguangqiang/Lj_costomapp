package com.defence.costomapp.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/6/29 and 16:19
 * Used
 */
public class LineChartBean {



    private List<List<String>> right_zhi;
    private List<List<String>> left_zhi;


    public List<List<String>> getRight_zhi() {
        if (right_zhi == null) {
            return new ArrayList<>();
        }
        return right_zhi;
    }

    public void setRight_zhi(List<List<String>> right_zhi) {
        this.right_zhi = right_zhi;
    }


    public List<List<String>> getLeft_zhi() {
        if (left_zhi == null) {
            return new ArrayList<>();
        }
        return left_zhi;
    }

    public void setLeft_zhi(List<List<String>> left_zhi) {
        this.left_zhi = left_zhi;
    }
}
