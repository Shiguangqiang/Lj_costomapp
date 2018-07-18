package com.defence.costomapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Sgq on 2018/2/2.
 */

@Database(name = MachineDatabase.MACHINESNAME, version = MachineDatabase.VERSION)
public class MachineDatabase {
    public static final String MACHINESNAME = "machinesname-db";

    public static final int VERSION = 1;
}
