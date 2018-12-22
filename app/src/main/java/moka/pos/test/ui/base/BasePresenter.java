package moka.pos.test.ui.base;

/**
 * Created by karthikeyan on 23/1/18.
 */

public class BasePresenter<V extends BaseView> implements MvpPresenter<V> {

    private V mViewInteractor;

    @Override
    public void attachViewInteractor(V view) {
        mViewInteractor = view;
    }

    /**
     * Don't detach the ViewModel from Presenter, Since Presenter can holds reference to view model
     * irrespective of Activity/Fragment lifecycle
     */
    @Override
    public void detachViewInteractor() {
        if (!(mViewInteractor instanceof BaseViewModel)) {
            mViewInteractor = null;
        }
    }

    @Override
    public V getViewInteractor() {
        return mViewInteractor;
    }

    public boolean isViewAttached() {
        return mViewInteractor != null;
    }

}
