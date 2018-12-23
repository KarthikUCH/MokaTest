package moka.pos.test.ui.discounts;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IDiscountPresenter<VI extends IDiscountView> extends MvpPresenter<VI> {

    void getDiscounts();
}
