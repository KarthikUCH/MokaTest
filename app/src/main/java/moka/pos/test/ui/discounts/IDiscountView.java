package moka.pos.test.ui.discounts;

import java.util.ArrayList;

import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.base.MvpView;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IDiscountView extends MvpView {

    void displayDiscounts(ArrayList<Discount> discountList);
}
