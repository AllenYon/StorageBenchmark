package com.alin.benchmark.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

    private Dao<User, Integer> userDao = null;

    /**
     * @param context
     */
    public OrmHelper(Context context) {
        super(context, "ormlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource arg1) {
        try {
            TableUtils.createTable(arg1, User.class);
            System.out.println("orm:创建数据库成功");
        } catch (Exception e) {
            System.out.println("orm:创建数据库失败");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
    }

    /**
     * @return the userDao
     */
    public Dao<User, Integer> getUserDao() {
        try {
            if (userDao == null) {
                userDao = getDao(User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao;
    }


}
