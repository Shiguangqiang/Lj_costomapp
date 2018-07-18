package com.defence.costomapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Sgq on 2018/2/2.
 */
@Database(name = GoodDatabase.GOODSNAME, version = GoodDatabase.VERSION)
public class GoodDatabase {
    public static final String GOODSNAME = "goodsname-db";

    public static final int VERSION = 1;
}
