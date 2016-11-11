package android.dstyo.com.androidtest.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.util.ConnectionDetector;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public abstract class BaseFragment extends Fragment implements ScreenBehaviorInterface {

    private ProgressDialog progressDialog;

    /**
     * Initialize Activity's toolbar.
     *
     * @param toolbar    the Toolbar view object
     * @param titleResId the string Title to be set on toolbar
     */
    protected void initToolbar(Toolbar toolbar, int titleResId) {
        ActionBar actionBar = initActionBar(toolbar);

        if (null != actionBar) {
            actionBar.setTitle(titleResId);
        }
    }

    private ActionBar initActionBar(Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (null != actionBar) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return actionBar;
    }

    @Override
    public void showProgressLoading(String message) {
        progressDialog = ProgressDialog.show(getActivity(), "", message, true);
    }

    @Override
    public void hideProgressLoading() {
        if (null != progressDialog) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if ((inputMethodManager.isAcceptingText()) && (getView() != null)) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean isInternetPresent() {
        if (getContext() == null) {
            return false;
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(getContext());
        return connectionDetector.isConnectingToInternet();
    }

    @Override
    public void setInternetTimedOut(View view) {
        hideProgressLoading();
        Toast.makeText(getContext(), getResources().getString(R.string.msg_internet_timed_out), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoInternetConnection(View view) {
        hideProgressLoading();
        Toast.makeText(getContext(), getResources().getString(R.string.msg_no_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnIntentOK() {
        Intent returnIntent = new Intent();
        int resultCode = Activity.RESULT_OK;

        Activity activity = getActivity();

        if (null != activity) {
            getActivity().setResult(resultCode, returnIntent);
            getActivity().finish();
        }
    }
}
