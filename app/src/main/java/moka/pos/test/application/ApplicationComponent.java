package moka.pos.test.application;

import javax.inject.Singleton;

import dagger.Component;
import moka.pos.test.ui.addtocart.AddToCartDialogFragment;
import moka.pos.test.ui.discounts.DiscountListFragment;
import moka.pos.test.ui.main.MainActivity;
import moka.pos.test.ui.item.ItemListFragment;
import moka.pos.test.ui.shoppingcart.ShoppingCartFragment;

/**
 * Created by karthikeyan on 23/1/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);

    void inject(ItemListFragment fragment);

    void inject(DiscountListFragment fragment);

    void inject(ShoppingCartFragment fragment);

    void inject(AddToCartDialogFragment dialogFragment);
}
