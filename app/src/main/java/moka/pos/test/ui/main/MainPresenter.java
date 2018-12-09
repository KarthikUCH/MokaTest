package moka.pos.test.ui.main;

import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class MainPresenter<V extends IMainView> extends BasePresenter<V> implements IMainPresenter<V> {

    @Override
    public void onClickDiscounts() {
        getMvpView().showToast("Displaying Discount List");
        getMvpView().displayDiscountList();
    }

    @Override
    public void onClickAllItems() {
        getMvpView().showToast("Displaying All Items List");
        getMvpView().displayAllItems();
    }

    @Override
    public void onClickToolbarBack() {
        getMvpView().showToast("Displaying Library List");
        getMvpView().displayLibrary();
    }

}
