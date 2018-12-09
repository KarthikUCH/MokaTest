package moka.pos.test.ui.base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import moka.pos.test.application.ApplicationComponent;

/**
 * Created by raju on 8/12/18.
 */
public abstract class BaseVMActivity<VM extends BaseViewModel> extends BaseActivity {

    private VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeViewModel();
    }

    protected void subscribeViewModel() {
        getViewModel().getmEventLiveData().observe(this, new Observer<EventData>() {
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

    public abstract VM getViewModel();

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
