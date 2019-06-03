package com.android.library.db;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.library.db.annotation.KeyboardType;
import com.android.library.db.entity.DaoMaster;
import com.android.library.db.entity.DaoSession;
import com.android.library.db.entity.KeyboardEntity;
import com.android.library.db.entity.KeyboardEntityDao;
import com.android.library.db.entity.KeyboardV2EntityDao;

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

    private static KeyboardEntityDao getKeyboardDao() {
        return getDaoSession().getKeyboardEntityDao();
    }

    private static KeyboardV2EntityDao getKeyboardV2Dao() {
        return getDaoSession().getKeyboardV2EntityDao();
    }

    public static void putKeyboardV1(KeyboardEntity keyboardEntity) {
        putKeyboard(KeyboardType.V1, keyboardEntity);
    }

    public static void putKeyboardV2(KeyboardEntity keyboardEntity) {
        putKeyboard(KeyboardType.V2, keyboardEntity);
    }

    public static void putKeyboard(@KeyboardType int type, KeyboardEntity entity) {
        switch (type) {
            case KeyboardType.V1:
                getKeyboardDao().insert(entity);
                break;
            case KeyboardType.V2:
                getKeyboardV2Dao().insert(Util.transformV2(entity));
                break;
        }
    }

    public static void updateKeyboardV1(KeyboardEntity keyboardEntity) {
        updateKeyboard(KeyboardType.V1, keyboardEntity);
    }

    public static void updateKeyboardV2(KeyboardEntity keyboardEntity) {
        updateKeyboard(KeyboardType.V2, keyboardEntity);
    }

    public static void updateKeyboard(@KeyboardType int type, KeyboardEntity entity) {
        switch (type) {
            case KeyboardType.V1:
                getKeyboardDao().update(entity);
                break;
            case KeyboardType.V2:
                getKeyboardV2Dao().update(Util.transformV2(entity));
                break;
        }
    }

    public static void putOrReplaceKeyboardV1(KeyboardEntity keyboardEntity) {
        putOrReplaceKeyboard(KeyboardType.V2, keyboardEntity);
    }

    public static void putOrReplaceKeyboardV2(KeyboardEntity keyboardEntity) {
        putOrReplaceKeyboard(KeyboardType.V2, keyboardEntity);
    }

    public static void putOrReplaceKeyboard(@KeyboardType int type, KeyboardEntity entity) {
        switch (type) {
            case KeyboardType.V1:
                getKeyboardDao().insertOrReplace(entity);
                break;
            case KeyboardType.V2:
                getKeyboardV2Dao().insertOrReplace(Util.transformV2(entity));
                break;
        }
    }

    public static KeyboardEntity queryKeyboardV1(String key) {
        return queryKeyboard(KeyboardType.V1, key);
    }

    public static KeyboardEntity queryKeyboardV2(String key) {
        return queryKeyboard(KeyboardType.V2, key);
    }

    public static KeyboardEntity queryKeyboard(@KeyboardType int type, String key) {
        switch (type) {
            case KeyboardType.V1:
                return getKeyboardDao().queryBuilder().where(KeyboardEntityDao.Properties.MarkingGroupId.eq(key)).unique();
            case KeyboardType.V2:
                return Util.transformV1(getKeyboardV2Dao().queryBuilder().where(KeyboardV2EntityDao.Properties.TopicId.eq(key)).unique());
        }
        return null;
    }

    public static Database getDatabase() {
        return getDaoSession().getDatabase();
    }

    public static void deleteAll() {
        getDaoSession().clear();
    }

    public static void deleteKeyboard() {
        try {
            getKeyboardDao().deleteAll();
            getKeyboardV2Dao().deleteAll();
        } catch (Exception ignored) {

        }
    }

}
