package moka.pos.test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import moka.pos.test.data.DbConstants;
import moka.pos.test.data.entity.Item;

/**
 * Created by raju on 6/1/19.
 */
public class SqliteDatabaseTestHelper {


    public static void createTable(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL(DbConstants.SQL_CREATE_ITEMS_TABLE);
        db.execSQL(DbConstants.SQL_CREATE_SHOPPING_CART_TABLE);

        db.close();
    }

    public static void dropTable(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL(DbConstants.SQL_DROP_ITEMS_TABLE);
        db.execSQL(DbConstants.SQL_DROP_SHOPPING_CART_TABLE);

        db.close();
    }

    public static long insertItem(SqliteTestDbOpenHelper helper, Item item) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("item_id", item.getId());
        values.put("album_id", item.getAlbumId());
        values.put("title", item.getTitle());
        values.put("price", item.getPrice());
        values.put("url", item.getUrl());
        values.put("thumbnail_url", item.getThumbnailUrl());

        return db.insert(DbConstants.Tables.ITEMS, null, values);
    }

}
