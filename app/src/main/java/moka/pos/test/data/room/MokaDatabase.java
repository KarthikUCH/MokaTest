package moka.pos.test.data.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import moka.pos.test.data.DbConstants;
import moka.pos.test.data.dao.CartItemDao;
import moka.pos.test.data.dao.ItemDao;
import moka.pos.test.data.entity.CartItem;
import moka.pos.test.data.entity.Item;

/**
 * Created by raju on 30/12/18.
 */

@Database(entities = {Item.class, CartItem.class}, version = 2)
public abstract class MokaDatabase extends RoomDatabase {

    private static final Object sLock = new Object();

    private static MokaDatabase INSTANCE;

    public abstract ItemDao itemDao();

    public abstract CartItemDao cartItemDao();

    @VisibleForTesting
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };


    public static MokaDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MokaDatabase.class, DbConstants.DB_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
            return INSTANCE;
        }
    }
}
