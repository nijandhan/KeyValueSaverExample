package com.sulekha.keyvaluecache.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nijandhanl on 7/21/17.
 */
@DatabaseTable(tableName = "keyValue")
public class KeyValue {

    @DatabaseField(id = true)
    String id;
    @DatabaseField
    String value;

    public KeyValue(){

    }

    public KeyValue(String key, String value){
        this.id = key;
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
