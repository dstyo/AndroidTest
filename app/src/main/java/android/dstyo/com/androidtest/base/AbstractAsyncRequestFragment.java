package android.dstyo.com.androidtest.base;

import android.dstyo.com.androidtest.api.core.AsyncRestClientManager;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public abstract class AbstractAsyncRequestFragment
        extends BaseFragment
        implements TagInterface {

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        AsyncRestClientManager.cancelRequestsByTAG(getTagRequest());
    }
}
