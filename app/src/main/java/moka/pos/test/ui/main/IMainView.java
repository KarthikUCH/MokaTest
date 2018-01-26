package moka.pos.test.ui.main;

import moka.pos.test.network.model.Item;
import moka.pos.test.ui.base.MvpView;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IMainView extends MvpView {

    void displayLibrary();

    void displayAllItems();

    void displayDiscountList();

    void displayShoppingCart();

    void displayAddToCartDialog(int itemId, String title, int price, int quantity, double discount, boolean editFlag);
}
