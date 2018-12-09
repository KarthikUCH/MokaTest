package moka.pos.test.ui.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;

/**
 * Created by raju on 8/12/18.
 */
public class BaseViewModel extends ViewModel implements MvpView {
    private MutableLiveData<EventData> mEventLiveData = new MutableLiveData<>();

    public LiveData<EventData> getmEventLiveData() {
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


class EventData {
    enum Event {SHOW_TOAST, SHOW_LOADING, HIDE_LOADING, SHOW_DIALOG, FINISH}

    public EventData(Event event, String title, String message) {
        this.event = event;
        this.title = title;
        this.message = message;
    }

    public Event event;
    public String title;
    public String message;

    public Event getEvent() {
        return event;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
