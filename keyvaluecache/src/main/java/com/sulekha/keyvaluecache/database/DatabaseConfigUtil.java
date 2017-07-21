package com.sulekha.keyvaluecache.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.sulekha.keyvaluecache.database.entity.KeyValue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[]{
            KeyValue.class
    };

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile(new File("keyvaluecache/src/main/res/raw/ormlite_config.txt"), classes);
    }
}