package moka.pos.test.ui.discounts;

import moka.pos.test.ui.base.MvpPresenter;
import moka.pos.test.ui.discounts.IDiscountView;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IDiscountPresenter<V extends IDiscountView> extends MvpPresenter<V> {

    void getDiscounts();
}
