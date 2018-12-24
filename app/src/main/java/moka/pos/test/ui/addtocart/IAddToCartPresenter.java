package moka.pos.test.ui.addtocart;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public interface IAddToCartPresenter<VI extends IAddToCartView> extends MvpPresenter<VI> {

    void onClickIncrement();

    void onClickDecrement();

    void onclickSave();

    void onClickCancel();

    void onQuantityEdited(String quantity);
}
