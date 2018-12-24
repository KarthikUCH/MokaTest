package moka.pos.test.ui.shoppingcart;

import java.util.ArrayList;

import moka.pos.test.data.model.CartItem;
import moka.pos.test.ui.base.BaseView;

/**
 * Created by karthikeyan on 25/1/18.
 */

public interface IShoppingCartView extends BaseView {

    void displayCartItems(ArrayList<CartItem> items);

    /**
     * Total amount after applying discount
     *
     * @param total
     */
    void displayFinalTotalAmount(double total);


}
