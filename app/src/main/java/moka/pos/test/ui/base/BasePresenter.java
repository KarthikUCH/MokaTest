package moka.pos.test.ui.base;

import moka.pos.test.ui.base.vm.BaseViewModel;

/**
 * Created by karthikeyan on 23/1/18.
 */

public class BasePresenter<VI extends BaseView> implements MvpPresenter<VI> {

    private VI mViewInteractor;

    @Override
    public void attachViewInteractor(VI view) {
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
    public VI getViewInteractor() {
        return mViewInteractor;
    }

    public boolean isViewAttached() {
        return mViewInteractor != null;
    }

}
