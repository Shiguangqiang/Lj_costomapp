package com.defence.costomapp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/6/29 and 16:19
 * Used
 */
public class LineChartBean {

    private List<String> link1List;

    public List<String> getLink1List() {
        if (link1List == null) {
            return new ArrayList<>();
        }
        return link1List;
    }

    public void setLink1List(List<String> link1List) {
        this.link1List = link1List;
    }


}
