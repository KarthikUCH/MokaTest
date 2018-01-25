package moka.pos.test.ui.main;

import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class MainPresenter<V extends IMainView> extends BasePresenter<V> implements IMainPresenter<V> {

    @Override
    public void attachView(V view) {
        super.attachView(view);

        getMvpView().displayLibrary();
        getMvpView().displayShoppingCart();
    }

    @Override
    public void onClickDiscounts() {
        getMvpView().displayDiscountList();
    }

    @Override
    public void onClickAllItems() {
        getMvpView().displayAllItems();
    }

    @Override
    public void onClickToolbarBack() {
        getMvpView().displayLibrary();
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
