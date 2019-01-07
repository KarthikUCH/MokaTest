/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moka.pos.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import moka.pos.test.data.DbConstants;

/**
 * Helper class for creating the test database version 1 with SQLite.
 */
public class SqliteTestDbOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public SqliteTestDbOpenHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.SQL_CREATE_ITEMS_TABLE);
        db.execSQL(DbConstants.SQL_CREATE_SHOPPING_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
        //db.execSQL(DbConstants.SQL_DROP_ITEMS_TABLE);
        //db.execSQL(DbConstants.SQL_DROP_SHOPPING_CART_TABLE);
        //onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
        //db.execSQL(DbConstants.SQL_DROP_ITEMS_TABLE);
        //db.execSQL(DbConstants.SQL_DROP_SHOPPING_CART_TABLE);
        //onCreate(db);
    }

}
