package moka.pos.test.ui.shoppingcart;

import moka.pos.test.data.model.CartItem;
import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 25/1/18.
 */

public interface IShoppingCartPresenter<VI extends IShoppingCartView> extends MvpPresenter<VI> {

    void getCartItems();

    CartItem getInitialTotalItem(double initialTotalAmount);

    CartItem getDiscountTotalItem(double discountTotalAmount);

    void onClickClearButton();
}
