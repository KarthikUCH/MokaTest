package moka.pos.test.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.application.MokaApplication;

/**
 * Created by karthikeyan on 23/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponent(((MokaApplication) getApplication()).getApplicationComponent());
    }

    protected abstract void injectComponent(ApplicationComponent component);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////                  METHODS FROM @MvpView INTERFACE                       ////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishView() {
        finish();
    }
}
