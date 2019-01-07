package moka.pos.test.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import moka.pos.test.data.entity.Item;

/**
 * Created by raju on 30/12/18.
 */

@Dao
public interface ItemDao {

    @Query("SELECT * FROM ITEMS")
    List<Item> getItems();

    @Insert
    void insertItems(List<Item> items);

}
