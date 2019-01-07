package moka.pos.test.application;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import moka.pos.test.data.DbManager;
import moka.pos.test.data.ItemsManager;
import moka.pos.test.data.ShoppingCartManager;
import moka.pos.test.data.room.MokaDatabase;
import moka.pos.test.network.RestServiceFactory;
import moka.pos.test.ui.addtocart.AddToCartDialogPresenter;
import moka.pos.test.ui.addtocart.IAddToCartPresenter;
import moka.pos.test.ui.discounts.DiscountPresenter;
import moka.pos.test.ui.discounts.IDiscountPresenter;
import moka.pos.test.ui.item.IItemListPresenter;
import moka.pos.test.ui.item.ItemListPresenter;
import moka.pos.test.ui.main.IMainPresenter;
import moka.pos.test.ui.main.MainPresenter;
import moka.pos.test.ui.shoppingcart.IShoppingCartPresenter;
import moka.pos.test.ui.shoppingcart.ShoppingCartPresenter;

/**
 * Created by karthikeyan on 23/1/18.
 */

@Module
public class ApplicationModule {

    private Application mApp;

    public ApplicationModule(Application mApp) {
        this.mApp = mApp;
    }

    @Singleton
    @Provides
    RestServiceFactory providesRestServiceFactory() {
        return new RestServiceFactory.Impl();
    }

    @Provides
    SupportSQLiteDatabase providesSqLiteDatabase() {
        return DbManager.getInstance(mApp).getDbHelper();
    }

    @Provides
    IMainPresenter providesMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    IItemListPresenter providesItemListPresenter(RestServiceFactory restServiceFactory, MokaDatabase mokaDatabase) {
        return new ItemListPresenter(restServiceFactory, mokaDatabase);
    }

    @Provides
    IDiscountPresenter providesDiscountPresenter() {
        return new DiscountPresenter();
    }

    @Provides
    IAddToCartPresenter providesAddToCartPresenter(ShoppingCartManager shoppingCartManager) {
        return new AddToCartDialogPresenter(shoppingCartManager);
    }

    @Singleton
    @Provides
    ItemsManager providesItemManager(SupportSQLiteDatabase sqLiteDatabase) {
        return new ItemsManager(sqLiteDatabase);
    }

    @Singleton
    @Provides
    ShoppingCartManager providesShoppingCartManager(SupportSQLiteDatabase sqLiteDatabase) {
        return new ShoppingCartManager(sqLiteDatabase);
    }

    @Provides
    IShoppingCartPresenter providesShoppingCartPresenter(ShoppingCartManager shoppingCartManager) {
        return new ShoppingCartPresenter(shoppingCartManager);
    }

    @Provides
    MokaDatabase providesMokaDatabase() {
        return MokaDatabase.getInstance(mApp);
    }

}
