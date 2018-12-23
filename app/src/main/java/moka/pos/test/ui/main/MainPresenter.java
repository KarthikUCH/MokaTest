package moka.pos.test.ui.main;

import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class MainPresenter<VI extends IMainView> extends BasePresenter<VI> implements IMainPresenter<VI> {

    @Override
    public void onClickDiscounts() {
        getViewInteractor().showToast("Displaying Discount List");
        getViewInteractor().displayDiscountList();
    }

    @Override
    public void onClickAllItems() {
        getViewInteractor().showToast("Displaying All Items List");
        getViewInteractor().displayAllItems();
    }

    @Override
    public void onClickToolbarBack() {
        getViewInteractor().showToast("Displaying Library List");
        getViewInteractor().displayLibrary();
    }

}
