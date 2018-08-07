package com.defence.costomapp.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Sgq on 2018/2/2.
 * 经纬度
 */
@Table(database = TrackDatabase.class)
public class TrackModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int num;
    @Column
    private String uid;
    @Column
    private String nowtime;
    @Column
    private String longitude;
    @Column
    private String latitude;

    public String getUid() {
        return uid == null ? "" : uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNowtime() {
        return nowtime == null ? "" : nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void insertData(String uid, String longitude, String latitude, String nowtime) {
        this.uid = uid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nowtime = nowtime;
    }
}
