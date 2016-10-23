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

}
