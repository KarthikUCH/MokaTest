package moka.pos.test.ui.base.vm;

/**
 * Created by raju on 23/12/18.
 */
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
