package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserListResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class UserRequest extends AbstractRequest {

    private static final String API_USERS_REQUEST = "/users";
    private static final String EXT_USERS_REQUEST = ".json";
    private static final String ACT_USERS_REQUEST = "/users/";

    public UserRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getUsers(JSONObject requestParams, UserListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_USERS_REQUEST + EXT_USERS_REQUEST),
                requestParams,
                responseHandler
        );
    }

    public void deleteUsers(int id, BooleanResponseHandler responseHandler) {
        delete(
                getCompleteUrl(ACT_USERS_REQUEST + id + EXT_USERS_REQUEST),
                null,
                responseHandler
        );
    }

    public void addUsers(JSONObject requestParams , BooleanResponseHandler responseHandler) {
        post(
                getCompleteUrl(API_USERS_REQUEST + EXT_USERS_REQUEST),
                requestParams,
                responseHandler
        );
    }

    public void getDetailUsers(int id, UserResponseHandler responseHandler) {
        get(
                getCompleteUrl(ACT_USERS_REQUEST + id + EXT_USERS_REQUEST),
                null,
                responseHandler
        );
    }


}
