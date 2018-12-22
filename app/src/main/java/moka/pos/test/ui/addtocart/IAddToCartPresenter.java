package moka.pos.test.ui.addtocart;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IAddToCartPresenter<V extends IAddToCartView> extends MvpPresenter<V> {

    void onClickIncrement();

    void onClickDecrement();

    void onclickSave();

    void onClickCancel();

    void onQuantityEdited(String quantity);
}
