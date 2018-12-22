package moka.pos.test.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.application.MokaApplication;

/**
 * Created by karthikeyan on 23/1/18.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponent(((MokaApplication) getActivity().getApplication()).getApplicationComponent());
    }

    protected abstract void injectComponent(ApplicationComponent component);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////                  METHODS FROM @MvpView INTERFACE                       ////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishView() {
        getActivity().finish();
    }


}
