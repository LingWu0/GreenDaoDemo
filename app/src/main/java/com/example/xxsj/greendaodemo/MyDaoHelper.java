package com.example.xxsj.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import bean.UserBean;
import dao.DaoMaster;
import dao.DaoSession;
import dao.UserBeanDao;

/**
 * Created by xxsj on 2017/12/11.
 */

public class MyDaoHelper {

    private static MyDaoHelper instance;
    private UserBeanDao dao;

    private MyDaoHelper(Context context) {
        try {
            MyDatabaseHelper helper = new MyDatabaseHelper(context, null, null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            DaoSession session = daoMaster.newSession();
            dao = session.getUserBeanDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyDaoHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MyDaoHelper(context);
        }
        return instance;
    }

    public void addData(UserBean bean) {
        if (dao != null && bean != null) {
            dao.insertOrReplace(bean);
        }
    }

    public void deleteData(long id) {
        if (dao != null && !TextUtils.isEmpty(id + "")) {
            dao.deleteByKey(id);
        }
    }

    public UserBean getDataById(long id) {
        if (dao != null && !TextUtils.isEmpty(id + "")) {
            return dao.load(id);
        }
        return null;
    }

    public List<UserBean> getAllData() {
        if (dao != null) {
            return dao.loadAll();
        }
        return null;
    }

    public long getTotalCount() {
        if (dao == null) {
            return 0;
        }
        QueryBuilder<UserBean> qb = dao.queryBuilder();
        return qb.buildCount().count();
    }

    public void deleteAll() {
        if (dao != null) {
            dao.deleteAll();
        }
    }
}
