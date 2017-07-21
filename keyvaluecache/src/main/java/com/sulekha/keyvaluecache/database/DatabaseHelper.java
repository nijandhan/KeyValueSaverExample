package com.sulekha.keyvaluecache.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sulekha.keyvaluecache.R;
import com.sulekha.keyvaluecache.database.entity.KeyValue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NijandhanL on 2/21/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "sulekha_keyvalue.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // handles database creation, it may look like the below
        try {
            TableUtils.createTable(connectionSource, KeyValue.class);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i(TAG,"old version : " + oldVersion);
        Log.i(TAG,"new version : " + newVersion);
        if (newVersion == 3) {
            try {
                TableUtils.dropTable(connectionSource, KeyValue.class, true);
                onCreate(database, connectionSource);
            } catch (SQLException e) {
                Log.e(TAG,e.toString());
            }
        }
    }

    public <T> void deleteRow(Class<T> clazz, String column, Object value) {
        try {
            DeleteBuilder<T, ?> deleteBuilder = getDao(clazz).deleteBuilder();
            deleteBuilder.where().eq(column, value);
            deleteBuilder.delete();
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
    }

    public <T> T getObject(Class<T> clazz, String column, Object value) {
        T t = null;
        try {
            QueryBuilder<T, ?> queryBuilder = getDao(clazz).queryBuilder();
            queryBuilder.where().eq(column, value);
            queryBuilder.orderBy("id", false);
            queryBuilder.limit((long) 1);
            List<T> objectList = queryBuilder.query();
            if (objectList.size() > 0)
                return objectList.get(0);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
        return t;
    }

    public <T> void deleteAllRows(Class<T> clazz) {
        try {
            TableUtils.clearTable(getConnectionSource(), clazz);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
    }

    public <T> void insertOrUpdtaeUserJourney(Class<T> clazz, T t) {
        try {
            getDao(clazz).createOrUpdate(t);
            Log.i(TAG,"database created");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
        }
    }

    public <T> T getLastInsertedObject(Class<T> clazz, String orderByColumn) {
        try {
            QueryBuilder<T, ?> queryBuilder = getDao(clazz).queryBuilder();
            queryBuilder.orderBy(orderByColumn, false);
            // queryBuilder.limit((long) 1);
            List<T> objectList = queryBuilder.query();
            Log.i(TAG,"row count : " + objectList.size());
            if (objectList.size() > 0) return objectList.get(0);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }

        return null;
    }

    public <T> List<T> getDataList(Class<T> clazz) {
        List<T> objectList = new ArrayList<>();
        try {
            for (T t : getDao(clazz))
                objectList.add(t);
        } catch (SQLException e) {
            Log.e(TAG,e.toString());
        }
        return objectList;
    }


    public <T> void deleteRow(Class<T> clazz, T t) {
        try {
            getDao(clazz).delete(t);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
        }
    }

}
