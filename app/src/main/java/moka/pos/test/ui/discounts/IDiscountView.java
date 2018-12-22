package moka.pos.test.ui.discounts;

import java.util.ArrayList;

import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.base.BaseView;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IDiscountView extends BaseView {

    void displayDiscounts(ArrayList<Discount> discountList);
}
