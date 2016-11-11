package android.dstyo.com.androidtest.api.handler;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.24
 */
public class BooleanResponseHandler extends AbstractResponseHandler<Boolean> {

    @Override
    protected Boolean parseResponse(JSONArray responseBody) {
        return !(null == responseBody);
    }

    @Override
    protected Boolean parseResponse(JSONObject responseBody) {
        return null;
    }
}
