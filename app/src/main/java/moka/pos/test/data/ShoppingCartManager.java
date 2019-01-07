package moka.pos.test.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQueryBuilder;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.WorkerThread;

import java.util.ArrayList;

import moka.pos.test.data.DbConstants.ItemsTable;
import moka.pos.test.data.DbConstants.ShoppingCartTable;
import moka.pos.test.data.DbConstants.Tables;
import moka.pos.test.data.model.CartItem;

/**
 * Created by karthikeyan on 25/1/18.
 */

public class ShoppingCartManager {

    private final SupportSQLiteDatabase mDbHelper;

    public ShoppingCartManager(SupportSQLiteDatabase mDbHelper) {
        this.mDbHelper = mDbHelper;
    }

    @WorkerThread
    public long insertCartItem(CartItem item) {
        ContentValues values = new ContentValues();
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_ID, item.getItemId());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_QUANTITY, item.getQuantity());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE, item.getTotalPrice());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS, item.getDiscount());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE, item.getDiscountRate());
        return mDbHelper.insert(Tables.SHOPPING_CART, SQLiteDatabase.CONFLICT_REPLACE, values);
    }

    @WorkerThread
    public int updateCartItem(CartItem item, double previousDiscount) {
        ContentValues values = new ContentValues();
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_ID, item.getItemId());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_QUANTITY, item.getQuantity());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE, item.getTotalPrice());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS, item.getDiscount());
        values.put(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE, item.getDiscountRate());
        return mDbHelper.update(Tables.SHOPPING_CART, SQLiteDatabase.CONFLICT_REPLACE, values
                , ShoppingCartTable.COLUMN_CART_ITEM_ID + " =? AND " + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + "=?"
                , new String[]{String.valueOf(item.getItemId()), String.valueOf(previousDiscount)});


    }

    @WorkerThread
    public ArrayList<CartItem> getItemsFromCart() {
        ArrayList<CartItem> items = new ArrayList<>();

        String query = "SELECT A." + ShoppingCartTable.COLUMN_CART_ITEM_ID + ",A." + ShoppingCartTable.COLUMN_CART_ITEM_QUANTITY
                + ",A." + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + ",A." + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE
                + ",A." + ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE
                + ",B." + ItemsTable.COLUMN_ITEM_TITLE + ",B." + ItemsTable.COLUMN_ITEM_THUMBNAIL_URL
                + " FROM " + Tables.SHOPPING_CART + " A LEFT JOIN " + Tables.ITEMS + " B ON A.item_id = B.item_id "
                + " ORDER BY A." + ShoppingCartTable._ID + " ASC";

        Cursor cursor = mDbHelper.query(query);

        while (cursor.moveToNext()) {
            items.add(CursorUtil.getItemFromCart(cursor, true));
        }
        if (cursor != null) {
            cursor.close();
        }

        return items;
    }

    @WorkerThread
    public CartItem getItemFormCart(int itemId, double discount) {
        CartItem item = null;
        /*Cursor cursor = mDbHelper.query(Tables.SHOPPING_CART, null, ShoppingCartTable.COLUMN_CART_ITEM_ID + " =? AND " + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + " =?"
                , new String[]{String.valueOf(itemId), String.valueOf(discount)}, null, null, null);*/

        SupportSQLiteQuery query = SupportSQLiteQueryBuilder.builder(Tables.SHOPPING_CART)
                .selection(ShoppingCartTable.COLUMN_CART_ITEM_ID + " =? AND "
                        + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS + " =?", new String[]{String.valueOf(itemId), String.valueOf(discount)})
                .create();
        Cursor cursor = mDbHelper.query(query);
        if (cursor.moveToFirst()) {
            item = CursorUtil.getItemFromCart(cursor, false);
        }

        if (cursor != null) {
            cursor.close();
        }

        return item;
    }

    @WorkerThread
    public int clearShoppingCart() {
        return mDbHelper.delete(Tables.SHOPPING_CART, null, null);
    }

    @WorkerThread
    public double getInitialTotal() {
        double total = 0;
        String query = "SELECT SUM(" + ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE + ") FROM " + Tables.SHOPPING_CART;
        Cursor cursor = mDbHelper.query(query);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        return total;
    }

    @WorkerThread
    public double getDiscountTotal() {
        double total = 0;
        String query = "SELECT SUM(" + ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE + ") FROM " + Tables.SHOPPING_CART;
        Cursor cursor = mDbHelper.query(query);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        return total;
    }
}
