package android.dstyo.com.androidtest.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

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
}
