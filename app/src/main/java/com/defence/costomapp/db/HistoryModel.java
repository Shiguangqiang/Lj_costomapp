package com.defence.costomapp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Sgq on 2018/2/2.
 * 商品组
 */
@Table(database = GoodDatabase.class)
public class HistoryModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int num;
    @Column
    private String jsonGoods;
    @Column
    private String goodName;

    public String getGoodName() {
        return goodName == null ? "" : goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJsonGoods() {
        return jsonGoods == null ? "" : jsonGoods;
    }

    public void setJsonGoods(String jsonGoods) {
        this.jsonGoods = jsonGoods;
    }

    public void insertData(String goodName, String jsonGoods) {
        this.goodName = goodName;
        this.jsonGoods = jsonGoods;
    }
}
