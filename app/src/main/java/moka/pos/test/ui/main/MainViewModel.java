package moka.pos.test.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import moka.pos.test.ui.base.BaseViewModel;

/**
 * Created by raju on 8/12/18.
 */
public class MainViewModel extends BaseViewModel implements IMainView {

    public enum ToDoStatus {DISPLAY_LIBRARY, DISPLAY_ALL_ITEMS, DISPLAY_DISCOUNT_LIST}

    private MutableLiveData<ToDoStatus> mToDoStatus = new MutableLiveData<>();

    public LiveData<ToDoStatus> getToDoStatus() {
        return mToDoStatus;
    }

    @Override
    public void displayLibrary() {
        mToDoStatus.setValue(ToDoStatus.DISPLAY_LIBRARY);
    }

    @Override
    public void displayAllItems() {
        mToDoStatus.setValue(ToDoStatus.DISPLAY_ALL_ITEMS);
    }

    @Override
    public void displayDiscountList() {
        mToDoStatus.setValue(ToDoStatus.DISPLAY_DISCOUNT_LIST);
    }
}
