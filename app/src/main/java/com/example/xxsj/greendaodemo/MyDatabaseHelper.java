package com.example.xxsj.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dao.DaoMaster;

/**
 * Created by xxsj on 2017/12/11.
 */

public class MyDatabaseHelper extends DaoMaster.OpenHelper{

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // 当数据库更新时进行的操作
                break;
        }
    }
}
