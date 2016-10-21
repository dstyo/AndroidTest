package android.dstyo.com.androidtest.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public abstract class BaseFragment extends Fragment {

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
}
