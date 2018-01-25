package moka.pos.test.data;

import android.database.Cursor;

import moka.pos.test.data.model.CartItem;
import moka.pos.test.data.DbConstants.ShoppingCartTable;
import moka.pos.test.data.DbConstants.ItemsTable;
import moka.pos.test.network.model.Item;

/**
 * Created by karthikeyan on 25/1/18.
 */

public class CursorUtil {

    public static Item getItem(Cursor cursor) {
        Item item = new Item();

        item.setId(cursor.getInt(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_ID)));
        item.setAlbumId(cursor.getInt(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_ALBUM_ID)));
        item.setTitle(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_TITLE)));
        item.setPrice(cursor.getInt(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_PRICE)));
        item.setUrl(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_URL)));
        item.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_THUMBNAIL_URL)));

        return item;
    }

    public static CartItem getItemFromCart(Cursor cursor, boolean joinTable) {
        CartItem item = new CartItem();

        item.setItemId(cursor.getInt(cursor.getColumnIndex(ShoppingCartTable.COLUMN_CART_ITEM_ID)));
        item.setQuantity(cursor.getInt(cursor.getColumnIndex(ShoppingCartTable.COLUMN_CART_ITEM_QUANTITY)));
        item.setTotalPrice(cursor.getInt(cursor.getColumnIndex(ShoppingCartTable.COLUMN_CART_ITEM_TOTAL_PRICE)));
        item.setDiscount(cursor.getLong(cursor.getColumnIndex(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNTS)));
        item.setDiscountRate(cursor.getLong(cursor.getColumnIndex(ShoppingCartTable.COLUMN_CART_ITEM_DISCOUNT_RATE)));
        if (joinTable) {
            item.setItemTitle(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_TITLE)));
            item.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ITEM_THUMBNAIL_URL)));
        }

        return item;
    }
}
