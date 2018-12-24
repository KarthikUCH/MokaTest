package moka.pos.test.ui.base;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface MvpPresenter<VI extends BaseView> {

    void attachViewInteractor(VI view);

    void detachViewInteractor();

    VI getViewInteractor();

    boolean isViewAttached();
}
