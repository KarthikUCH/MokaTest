package moka.pos.test.ui.base.vm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.reflect.ParameterizedType;

import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.ui.base.BaseFragment;


/**
 * Created by raju on 23/12/18.
 */
public abstract class BaseVmFragment<VM extends BaseViewModel> extends BaseFragment {
    protected VM mViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        observeViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
    }

    protected void observeViewModel() {

        mViewModel.getEventLiveData().observe(this, new Observer<EventData>() {
            @Override
            public void onChanged(@Nullable EventData eventData) {
                switch (eventData.event) {
                    case SHOW_TOAST:
                        showToast(eventData);
                        break;
                    case SHOW_LOADING:
                        showLoading(eventData);
                        break;
                    case HIDE_LOADING:
                        hideLoading();
                        break;
                    case SHOW_DIALOG:
                        showDialog(eventData);
                        break;
                    case FINISH:
                        finishView();
                        break;
                }
            }
        });
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {

    }

    private Class<VM> getViewModelClass() {
        return (Class<VM>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private void showToast(EventData eventData) {
        showToast(eventData.getMessage());
    }

    private void showLoading(EventData eventData) {
        // TODO
    }

    private void hideLoading() {
        // TODO
    }

    private void showDialog(EventData eventData) {
        // TODO
    }
}
