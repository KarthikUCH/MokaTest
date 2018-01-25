package moka.pos.test.ui.main;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IMainPresenter<V extends IMainView> extends MvpPresenter<V> {

    void onClickDiscounts();

    void onClickAllItems();

    void onClickToolbarBack();
}
