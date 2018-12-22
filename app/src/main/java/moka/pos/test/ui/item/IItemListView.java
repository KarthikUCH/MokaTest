package moka.pos.test.ui.item;

import java.util.List;

import moka.pos.test.network.model.Item;
import moka.pos.test.ui.base.BaseView;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface IItemListView extends BaseView {

    void onAllItemsSuccess(List<Item> itemList);

    void onAllItemsFailure(String errorMsg);

    boolean isNetworkConnected();
}
