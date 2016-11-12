package android.dstyo.com.androidtest.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.util.ConnectionDetector;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.12
 */
public class BaseAppCompactActivity extends AppCompatActivity implements ScreenBehaviorInterface {

    private ProgressDialog progressDialog;

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void showProgressLoading(String message) {
        progressDialog = ProgressDialog.show(this, "", message, true);
    }

    @Override
    public void hideProgressLoading() {
        if (null != progressDialog) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean isInternetPresent() {
        if (getApplicationContext() == null) {
            return false;
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(getApplicationContext());
        return connectionDetector.isConnectingToInternet();
    }

    @Override
    public void setInternetTimedOut() {
        hideProgressLoading();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_internet_timed_out), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoInternetConnection() {
        hideProgressLoading();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg_no_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnIntentOK() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
