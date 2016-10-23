package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.model.ModelFactory;
import android.dstyo.com.androidtest.model.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class UserListResponseHandler extends AbstractResponseHandler<List<User>> {

    @Override
    protected void onSuccess(JSONArray responseBody, List<User> userList) {
        int totalItems = userList.size();
        onSuccess(userList,totalItems);
    }

    @Override
    protected List<User> parseResponse(JSONArray responseBody) {
        if (null == responseBody) {
            return new ArrayList<>();
        }

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < responseBody.length(); i++) {
            String responseString = responseBody.optString(i);

            if (!responseString.equals("")) {
                User user = ModelFactory.create(User.class, responseString);
                userList.add(user);
            }
        }

        return userList;
    }
}
