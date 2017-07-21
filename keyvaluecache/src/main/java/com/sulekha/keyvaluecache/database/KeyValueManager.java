package com.sulekha.keyvaluecache.database;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.sulekha.keyvaluecache.database.entity.KeyValue;

/**
 * Created by nijandhanl on 7/21/17.
 */

public class KeyValueManager {

    private static final String TAG = "KeyValueManager";

    public static void putKeyValue(String key,String value){

    }

    public static <T> void putKeyValue(Context context, String key, T t){
        try {
            KeyValue keyValue = new KeyValue(key, toJsonString(t));
            DatabaseHelper db = new DatabaseHelper(context);
            db.insertOrUpdtaeUserJourney(KeyValue.class, keyValue);
            db.close();
        } catch (Exception e){
                Log.e(TAG,e.toString());
        }
    }

    public static <T> T getValue(Context context,String key,Class<T> clazz){
        try {
            DatabaseHelper db = new DatabaseHelper(context);
            KeyValue keyValue = db.getObject(KeyValue.class, "id", key);
            return getObjectFromJson(keyValue.getValue(),clazz);
        } catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return null;
    }

    public static <T> String toJsonString(T t) {
        return (new Gson()).toJson(t);
    }

    public static <T> T getObjectFromJson(String json, Class<T> clazz) {
        return (new Gson()).fromJson(json, clazz);
    }
}
