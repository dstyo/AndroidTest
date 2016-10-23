package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserListResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class UserRequest extends AbstractRequest {

    private static final String API_USERS_REQUEST = "/users.json";

    public UserRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getUsers(RequestParams requestParams, UserListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_USERS_REQUEST),
                requestParams,
                responseHandler
        );
    }
}
