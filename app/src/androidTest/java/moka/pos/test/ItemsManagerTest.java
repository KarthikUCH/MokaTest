package moka.pos.test;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import moka.pos.test.data.DbManager;
import moka.pos.test.data.ItemsManager;
import moka.pos.test.network.model.Item;

/**
 * Created by raju on 5/1/19.
 */
@RunWith(AndroidJUnit4.class)
public class ItemsManagerTest {

    private DbManager mDbManager;
    private ItemsManager mItemsManager;

    private static final Item ITEM = new Item(1, 1, "accusamus beatae",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952", 1000);

    private static final Item ITEM_2 = new Item(2, 2, "accusamus beatae",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952", 1000);


    @Before
    public void init() {
        mDbManager = DbManager.getInstance(InstrumentationRegistry.getTargetContext());
        mItemsManager = new ItemsManager(mDbManager.getDbHelper());
    }

    @After
    public void cleanUp() {
        mItemsManager.deleteAllItems();
    }

    @Test
    public void insertAndGetItem() {
        List<Item> addItems = new ArrayList<Item>();
        addItems.add(ITEM);
        mItemsManager.insertItems(addItems);

        List<Item> getItems = mItemsManager.getItems();

        assertEquals(getItems.size(), 1);
        assertEquals(getItems.get(0).getId(), 1);
    }

    @Test
    public void insertDuplicateTest() {
        List<Item> addItems = new ArrayList<Item>();
        addItems.add(ITEM);
        mItemsManager.insertItems(addItems);
        mItemsManager.insertItems(addItems);

        List<Item> getItems = mItemsManager.getItems();

        assertEquals(getItems.size(), 1);
    }

    @Test
    public void insertNewTest() {
        List<Item> addItems = new ArrayList<Item>();
        addItems.add(ITEM);
        addItems.add(ITEM_2);
        mItemsManager.insertItems(addItems);

        List<Item> getItems = mItemsManager.getItems();

        assertEquals(getItems.size(), 2);
    }

}
