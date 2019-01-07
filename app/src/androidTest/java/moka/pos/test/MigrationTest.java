package moka.pos.test;

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import moka.pos.test.data.ItemsManager;
import moka.pos.test.data.entity.Item;
import moka.pos.test.data.room.MokaDatabase;

import static moka.pos.test.data.room.MokaDatabase.MIGRATION_1_2;
import static org.junit.Assert.assertEquals;

/**
 * Created by raju on 5/1/19.
 */

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    private static final String TEST_DB_NAME = "test-db";

    private static final Item ITEM = new Item(1, 1, "Title", 1000L, "url", "thumbnail_url");

    @Rule
    public MigrationTestHelper mMigrationTestHelper =
            new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                    MokaDatabase.class.getCanonicalName(),
                    new FrameworkSQLiteOpenHelperFactory());

    // Helper for creating SQLite database in version 1
    private SqliteTestDbOpenHelper mSqliteTestDbHelper;

    private ItemsManager mItemsManager;

    @Before
    public void setUp() throws Exception {
        // To test migrations from version 1 of the database, we need to create the database
        // with version 1 using SQLite API
        mSqliteTestDbHelper = new SqliteTestDbOpenHelper(InstrumentationRegistry.getTargetContext(),
                TEST_DB_NAME);

        // We're creating the table for every test, to ensure that the table is in the correct state
        SqliteDatabaseTestHelper.createTable(mSqliteTestDbHelper);
    }

    @After
    public void tearDown() throws Exception {
        // Clear the database after every test
        SqliteDatabaseTestHelper.dropTable(mSqliteTestDbHelper);
    }

    @Test
    public void migrationFrom1To2_containsCorrectData() throws IOException {

        SqliteDatabaseTestHelper.insertItem(mSqliteTestDbHelper, ITEM);
        mMigrationTestHelper.runMigrationsAndValidate(TEST_DB_NAME, 2, true,
                MIGRATION_1_2);

        MokaDatabase latestDb = getMigratedRoomDatabase();
        List<Item> items = latestDb.itemDao().getItems();

        assertEquals(items.size(), 1);
        assertEquals(items.get(0).getId(), new Integer(1));
    }

    private MokaDatabase getMigratedRoomDatabase() {
        MokaDatabase database = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(),
                MokaDatabase.class, TEST_DB_NAME)
                .addMigrations(MIGRATION_1_2)
                .build();
        // close the database and release any stream resources when the test finishes
        mMigrationTestHelper.closeWhenFinished(database);
        return database;
    }
}
