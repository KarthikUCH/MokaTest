package moka.pos.test.ui.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.application.MokaApplication;

/**
 * Created by karthikeyan on 24/1/18.
 */

public abstract class BaseDialogFragment extends DialogFragment implements MvpView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectComponent(((MokaApplication) getActivity().getApplication()).getApplicationComponent());
    }

    protected abstract void injectComponent(ApplicationComponent component);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishView() {
        dismiss();
    }
}
