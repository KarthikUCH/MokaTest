package moka.pos.test.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import moka.pos.test.data.entity.CartItem;

/**
 * Created by raju on 30/12/18.
 */

@Dao
public interface CartItemDao {

    @Insert
    long insertCartItem(CartItem item);

    /*@Update("")
    int updateCartItem(CartItem item,double previousDiscount)*/
}
