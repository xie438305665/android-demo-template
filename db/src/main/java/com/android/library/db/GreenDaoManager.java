package com.android.library.db;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.library.db.entity.DaoMaster;
import com.android.library.db.entity.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * @author xcl
 */
public class GreenDaoManager {

    @SuppressLint("StaticFieldLeak")
    private static Context dbContext;
    private static final String SQL_NAME = "demo.template";

    public static void register(Context context) {
        dbContext = context.getApplicationContext();
    }

    private GreenDaoManager() {
    }

    private static DaoSession getDaoSession() {
        return SessionHolder.daoSession;
    }

    private static class SessionHolder {
        private static final DaoSession daoSession = new DaoMaster(
                new GreenDaoSQLiteOpenHelper(dbContext, SQL_NAME, null)
                        .getWritableDatabase()).newSession();
    }


    public static Database getDatabase() {
        return getDaoSession().getDatabase();
    }

    public static void deleteAll() {
        getDaoSession().clear();
    }
}
