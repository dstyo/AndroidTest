package android.dstyo.com.androidtest.base;

import android.view.View;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public interface ScreenBehaviorInterface {

    /**
     * Hide Keyboard from screen.
     */
    void hideKeyboard();

    /**
     * Show loading from given message.
     *
     * @param message the string that represent loading message
     */
    void showProgressLoading(String message);

    /**
     * Hide the current loading.
     */
    void hideProgressLoading();

    /**
     * Check whether internet is connected or not.
     *
     * @return  <code>true</code> if internet is present or
     *          <code>false</code> if internet is not available or context is missing.
     */
    boolean isInternetPresent();

    /**
     * Show message that internet connection has timed out.
     *
     * @param view the parent view to show snackBar message (usually CoordinatorLayout)
     */
    void setInternetTimedOut(View view);

    /**
     * Show message that no internet connection present.
     *
     * @param view the parent view to show snackBar message (usually CoordinatorLayout)
     */
    void setNoInternetConnection(View view);

    /**
     * Return <code>Activity.RESULT_OK</code> intent to Activity's caller.
     */
    void returnIntentOK();

}
