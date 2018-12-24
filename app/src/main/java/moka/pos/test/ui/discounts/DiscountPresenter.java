package moka.pos.test.ui.discounts;

import java.util.ArrayList;

import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class DiscountPresenter<VI extends IDiscountView> extends BasePresenter<VI> implements IDiscountPresenter<VI> {

    @Override
    public void attachViewInteractor(VI view) {
        super.attachViewInteractor(view);
        getDiscounts();
    }

    @Override
    public void getDiscounts() {
        ArrayList<Discount> list = new ArrayList<>();
        list.add(new Discount(1, "Discount A", 0));
        list.add(new Discount(2, "Discount B", 10));
        list.add(new Discount(3, "Discount C", 35.5));
        list.add(new Discount(4, "Discount D", 50));
        list.add(new Discount(5, "Discount E", 100));
        getViewInteractor().displayDiscounts(list);
    }

    @Override
    public void detachViewInteractor() {
        super.detachViewInteractor();
    }
}
