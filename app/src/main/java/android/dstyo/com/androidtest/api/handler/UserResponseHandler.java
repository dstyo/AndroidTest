package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.ModelFactory;
import android.dstyo.com.androidtest.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.12
 */
public class UserResponseHandler extends AbstractResponseHandler<User> {
    @Override
    protected User parseResponse(JSONArray responseBody) {
        return null;
    }

    @Override
    protected User parseResponse(JSONObject responseBody) {
        if (null == responseBody) {
            return null;
        }

        return ModelFactory.create(User.class, responseBody.toString());
    }
}
