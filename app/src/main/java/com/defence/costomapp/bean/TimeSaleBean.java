package com.defence.costomapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/15.
 */

public class TimeSaleBean {


    /**
     * sign : 1
     * message :
     * data : {"00:00:00-04:00:00":301,"04:00:00-08:00:00":121,"08:00:00-12:00:00":374,"12:00:00-16:00:00":619,"16:00:00-20:00:00":1378,"20:00:00-24:00:00":1116}
     * data_list : [301,121,374,619,1378,1116]
     * timelineN : 2018-03-15 11:03:48
     */

    private int sign;
    private String message;
    private DataBean data;
    private String timelineN;
    private List<Integer> data_list;

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTimelineN() {
        return timelineN;
    }

    public void setTimelineN(String timelineN) {
        this.timelineN = timelineN;
    }

    public List<Integer> getData_list() {
        return data_list;
    }

    public void setData_list(List<Integer> data_list) {
        this.data_list = data_list;
    }

    public static class DataBean {
        @SerializedName("00:00:00-04:00:00")
        private int _$_000000040000111; // FIXME check this code
        @SerializedName("04:00:00-08:00:00")
        private int _$_040000080000121; // FIXME check this code
        @SerializedName("08:00:00-12:00:00")
        private int _$_08000012000089; // FIXME check this code
        @SerializedName("12:00:00-16:00:00")
        private int _$_120000160000252; // FIXME check this code
        @SerializedName("16:00:00-20:00:00")
        private int _$_160000200000221; // FIXME check this code
        @SerializedName("20:00:00-24:00:00")
        private int _$_20000024000096; // FIXME check this code

        public int get_$_000000040000111() {
            return _$_000000040000111;
        }

        public void set_$_000000040000111(int _$_000000040000111) {
            this._$_000000040000111 = _$_000000040000111;
        }

        public int get_$_040000080000121() {
            return _$_040000080000121;
        }

        public void set_$_040000080000121(int _$_040000080000121) {
            this._$_040000080000121 = _$_040000080000121;
        }

        public int get_$_08000012000089() {
            return _$_08000012000089;
        }

        public void set_$_08000012000089(int _$_08000012000089) {
            this._$_08000012000089 = _$_08000012000089;
        }

        public int get_$_120000160000252() {
            return _$_120000160000252;
        }

        public void set_$_120000160000252(int _$_120000160000252) {
            this._$_120000160000252 = _$_120000160000252;
        }

        public int get_$_160000200000221() {
            return _$_160000200000221;
        }

        public void set_$_160000200000221(int _$_160000200000221) {
            this._$_160000200000221 = _$_160000200000221;
        }

        public int get_$_20000024000096() {
            return _$_20000024000096;
        }

        public void set_$_20000024000096(int _$_20000024000096) {
            this._$_20000024000096 = _$_20000024000096;
        }
    }
}
