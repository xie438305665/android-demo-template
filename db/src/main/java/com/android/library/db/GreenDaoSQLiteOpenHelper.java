package com.android.library.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.library.db.entity.DaoMaster;

import org.greenrobot.greendao.database.Database;

import static com.android.library.db.entity.DaoMaster.dropAllTables;

/**
 * @author xcl
 * @create 2018/12/20
 */
public class GreenDaoSQLiteOpenHelper extends DaoMaster.OpenHelper {
    GreenDaoSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        dropAllTables(db, true);
        onCreate(db);
//        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
//
//            @Override
//            public void onCreateAllTables(Database db, boolean ifNotExists) {
//                DaoMaster.createAllTables(db, ifNotExists);
//            }
//
//            @Override
//            public void onDropAllTables(Database db, boolean ifExists) {
//                DaoMaster.dropAllTables(db, ifExists);
//            }
//        }, KeyboardEntityDao.class, KeyboardV2EntityDao.class);
    }
}
