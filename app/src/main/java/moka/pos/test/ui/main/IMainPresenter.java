package moka.pos.test.ui.main;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IMainPresenter<VI extends IMainView> extends MvpPresenter<VI> {

    void onClickDiscounts();

    void onClickAllItems();

    void onClickToolbarBack();
}
