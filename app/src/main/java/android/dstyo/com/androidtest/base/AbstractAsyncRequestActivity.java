package android.dstyo.com.androidtest.base;

import android.dstyo.com.androidtest.api.core.AsyncRestClientManager;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.24
 */
public abstract class AbstractAsyncRequestActivity extends AppCompatActivity implements TagInterface{

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AsyncRestClientManager.cancelRequestsByTAG(getTagRequest());
    }
}
