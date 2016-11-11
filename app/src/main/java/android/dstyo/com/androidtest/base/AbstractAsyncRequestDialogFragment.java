package android.dstyo.com.androidtest.base;

import android.dstyo.com.androidtest.api.core.AsyncRestClientManager;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;
import android.support.v4.app.DialogFragment;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.24
 */
public abstract class AbstractAsyncRequestDialogFragment extends BaseDialogFragment
        implements TagInterface {

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        AsyncRestClientManager.cancelRequestsByTAG(getTagRequest());
    }
}
