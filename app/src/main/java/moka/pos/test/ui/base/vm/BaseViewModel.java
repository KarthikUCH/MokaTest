package moka.pos.test.ui.base.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;

import moka.pos.test.ui.base.BaseView;

/**
 * Created by raju on 8/12/18.
 */
public class BaseViewModel extends ViewModel implements BaseView {
    private MutableLiveData<EventData> mEventLiveData = new MutableLiveData<>();

    public LiveData<EventData> getEventLiveData() {
        return mEventLiveData;
    }

    @Override
    @MainThread
    public void showToast(String message) {
        EventData eventData = new EventData(EventData.Event.SHOW_TOAST, null, message);
        mEventLiveData.setValue(eventData);
    }

    @Override
    @MainThread
    public void finishView() {
        EventData eventData = new EventData(EventData.Event.FINISH, null, null);
        mEventLiveData.setValue(eventData);
    }
}


