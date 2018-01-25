package moka.pos.test.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import moka.pos.test.data.DbManager;
import moka.pos.test.data.ItemsManager;
import moka.pos.test.data.ShoppingCartManager;
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
    SQLiteDatabase providesSqLiteDatabase() {
        return DbManager.getInstance(mApp).getDbHelper();
    }

    @Provides
    IMainPresenter providesMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    IItemListPresenter providesItemListPresenter(RestServiceFactory restServiceFactory, ItemsManager itemsManager) {
        return new ItemListPresenter(restServiceFactory, itemsManager);
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
    ItemsManager providesItemManager(SQLiteDatabase sqLiteDatabase) {
        return new ItemsManager(sqLiteDatabase);
    }

    @Singleton
    @Provides
    ShoppingCartManager providesShoppingCartManager(SQLiteDatabase sqLiteDatabase) {
        return new ShoppingCartManager(sqLiteDatabase);
    }

    @Provides
    IShoppingCartPresenter providesShoppingCartPresenter(ShoppingCartManager shoppingCartManager) {
        return new ShoppingCartPresenter(shoppingCartManager);
    }

}
