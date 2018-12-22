package moka.pos.test.ui.base;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface MvpPresenter<V extends BaseView> {

    void attachViewInteractor(V view);

    void detachViewInteractor();

    V getViewInteractor();

    boolean isViewAttached();
}
