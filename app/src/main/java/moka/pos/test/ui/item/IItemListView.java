package moka.pos.test.ui.item;

import java.util.ArrayList;
import java.util.List;

import moka.pos.test.network.model.Item;
import moka.pos.test.ui.base.MvpView;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface IItemListView extends MvpView {

    void onAllItemsSuccess(List<Item> itemList);

    void onAllItemsFailure(String errorMsg);
}
