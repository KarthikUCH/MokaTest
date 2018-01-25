package moka.pos.test.data;

import android.provider.BaseColumns;

/**
 * Created by karthikeyan on 25/1/18.
 */

public class DbConstants {

    public static final String DB_NAME = "mokatest.db";
    public static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String LONG_TYPE = " LONG";
    private static final String AUTO_INCREMENT_TYPE = " AUTOINCREMENT";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ",";

    interface Tables {
        String ITEMS = "items";
        String SHOPPING_CART = "shopping_cart";
    }

    interface ItemsTable extends BaseColumns {
        String COLUMN_ITEM_ID = "item_id";
        String COLUMN_ITEM_ALBUM_ID = "album_id";
        String COLUMN_ITEM_TITLE = "title";
        String COLUMN_ITEM_PRICE = "price";
        String COLUMN_ITEM_URL = "url";
        String COLUMN_ITEM_THUMBNAIL_URL = "thumbnail_url";
    }

    interface ShoppingCartTable extends BaseColumns {
        String COLUMN_CART_ITEM_ID = "item_id";
        String COLUMN_CART_ITEM_QUANTITY = "quantity";
        String COLUMN_CART_ITEM_TOTAL_PRICE = "total_ptice";
        String COLUMN_CART_ITEM_DISCOUNTS = "discount";
        String COLUMN_CART_ITEM_DISCOUNT_RATE = "discount_rate";
    }

    // CREATE TABLE SQL QUERY

    public static final String SQL_CREATE_ITEMS_TABLE =
            "CREATE TABLE " + Tables.ITEMS + " ("
                    + ItemsTable._ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_ID + INTEGER_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_ALBUM_ID + INTEGER_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_TITLE + TEXT_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_PRICE + LONG_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_URL + TEXT_TYPE + COMMA_SEP
                    + ItemsTable.COLUMN_ITEM_THUMBNAIL_URL + TEXT_TYPE + COMMA_SEP
                    + " UNIQUE (" + ItemsTable.COLUMN_ITEM_ID + ") ON CONFLICT IGNORE)";


    public static final String SQL_CREATE_SHOPPING_CART_TABLE =
            "CREATE TABLE " + Tables.SHOPPING_CART + " ("
                    + ShoppingCartTable._ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT_TYPE + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_ID + INTEGER_TYPE + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_QUANTITY + INTEGER_TYPE + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE + INTEGER_TYPE + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + LONG_TYPE + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE + LONG_TYPE + COMMA_SEP
                    + " UNIQUE (" + ShoppingCartTable.COLUMN_CART_ITEM_ID + COMMA_SEP
                    + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + ") ON CONFLICT REPLACE)";


    // DROP TABLE SQL QUERY

    public static final String SQL_DROP_ITEMS_TABLE = "DROP TABLE IF EXISTS" + Tables.ITEMS;
    public static final String SQL_DROP_SHOPPING_CART_TABLE = "DROP TABLE IF EXISTS" + Tables.SHOPPING_CART;


    // INSERT QUERY

    public static final String INSERT_ITEM_QUERY = "INSERT INTO " + Tables.ITEMS + " ("
            + ItemsTable.COLUMN_ITEM_ID + COMMA_SEP
            + ItemsTable.COLUMN_ITEM_ALBUM_ID + COMMA_SEP
            + ItemsTable.COLUMN_ITEM_TITLE + COMMA_SEP
            + ItemsTable.COLUMN_ITEM_PRICE + COMMA_SEP
            + ItemsTable.COLUMN_ITEM_URL + COMMA_SEP
            + ItemsTable.COLUMN_ITEM_THUMBNAIL_URL
            + ") values(?,?,?,?,?,?)";

}
