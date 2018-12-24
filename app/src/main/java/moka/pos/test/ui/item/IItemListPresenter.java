package moka.pos.test.ui.item;

import moka.pos.test.ui.base.MvpPresenter;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface IItemListPresenter<VI extends IItemListView> extends MvpPresenter<VI> {

    void getAllItems();
}
