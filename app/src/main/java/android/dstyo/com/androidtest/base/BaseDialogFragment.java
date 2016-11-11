package android.dstyo.com.androidtest.base;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.util.ConnectionDetector;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Representing base class of AppCompatDialogFragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 */
public abstract class BaseDialogFragment
        extends AppCompatDialogFragment
        implements ScreenBehaviorInterface {

    private ProgressDialog progressDialog;

    /**
     * Return the application that owns this activity.
     *
     * @return the Application
     */
    public Application getApplication() {
        return getActivity().getApplication();
    }

    /**
     * Dismiss dialog fragment and notify target fragment with given request and result code.
     *
     * @param requestCode the request code number
     * @param resultCode  the result code {@link Activity}
     */
    protected void dismissAndNotifyTarget(int requestCode, int resultCode) {
        dismissAndNotifyTarget(requestCode, resultCode, getActivity().getIntent(), false);
    }

    /**
     * Dismiss dialog fragment and notify target fragment with given request and result code.
     *
     * @param requestCode    the request code number
     * @param resultCode     the result code {@link Activity}
     * @param intent         the intent to be sent
     * @param allowStateLoss the status to allowing state loss
     *                       {@link AppCompatDialogFragment#dismissAllowingStateLoss()}
     */
    protected void dismissAndNotifyTarget(int requestCode, int resultCode, Intent intent, boolean allowStateLoss) {
        hideProgressLoading();

        getTargetFragment().onActivityResult(requestCode, resultCode, intent);

        if (allowStateLoss) {
            dismissAllowingStateLoss();
        }
        else {
            dismiss();
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if ((inputMethodManager.isAcceptingText()) && (getView() != null)) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
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

    private void showSnackBarMessage(Snackbar snackbar) {
        View snackBarView = snackbar.getView();
        TextView tv = (TextView) snackBarView
                .findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);

        snackbar.show();
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
