package moka.pos.test.ui.base;

/**
 * Created by karthikeyan on 23/1/18.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    @Override
    public void attachView(V view) {
        mMvpView = view;
    }

    /**
     * Don't detach the ViewModel from Presenter, Since Presenter can holds reference to view model
     * irrespective of Activity/Fragment lifecycle
     */
    @Override
    public void detachView() {
        if (!(mMvpView instanceof BaseViewModel)) {
            mMvpView = null;
        }
    }

    @Override
    public V getMvpView() {
        checkViewAttached();
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
