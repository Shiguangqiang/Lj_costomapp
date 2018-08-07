package com.defence.costomapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Sgq on 2018/2/2.
 * 补货人员轨迹
 */

@Database(name = TrackDatabase.TRACKNAME, version = TrackDatabase.VERSION)
public class TrackDatabase {
    public static final String TRACKNAME = "trackname-db";

    public static final int VERSION = 1;
}
