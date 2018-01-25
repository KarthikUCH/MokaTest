package moka.pos.test.ui.shoppingcart;

import moka.pos.test.data.model.CartItem;
import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 25/1/18.
 */

public interface IShoppingCartPresenter<V extends IShoppingCartView> extends MvpPresenter<V> {

    void getCartItems();

    CartItem getInitialTotalItem(int initialTotalAmount);

    CartItem getDiscountTotalItem(int discountTotalAmount);

    void onClickClearButton();
}
