package moka.pos.test.ui.addtocart;

import moka.pos.test.ui.base.BaseView;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IAddToCartView extends BaseView {

    int getItemId();

    String getItemTitle();

    double getItemPrice();

    int getItemQuantity();

    double getItemDiscount();

    boolean isEdit();

    double getChosenDiscount();

    void setTitle(String title);

    void setQuantity(int quantity);

    void addedToCart();

}
