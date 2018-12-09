package moka.pos.test.ui.discounts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.base.BaseViewModel;

/**
 * Created by raju on 9/12/18.
 */
public class DiscountViewModel extends BaseViewModel implements IDiscountView {

    private MutableLiveData<ArrayList<Discount>> mDiscountListDate = new MutableLiveData<>();

    public LiveData<ArrayList<Discount>> getDiscountListDate() {
        return mDiscountListDate;
    }

    @Override
    public void displayDiscounts(ArrayList<Discount> discountList) {
        mDiscountListDate.setValue(discountList);
    }
}
