package moka.pos.test.ui.base;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    V getMvpView();

    boolean isViewAttached();

    void checkViewAttached() throws BasePresenter.MvpViewNotAttachedException;
}
