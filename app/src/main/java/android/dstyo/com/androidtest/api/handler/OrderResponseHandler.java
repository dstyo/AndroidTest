package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.ModelFactory;
import android.dstyo.com.androidtest.model.Order;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.12
 */
public class OrderResponseHandler extends AbstractResponseHandler<Order> {
    @Override
    protected Order parseResponse(JSONArray responseBody) {
        return null;
    }

    @Override
    protected Order parseResponse(JSONObject responseBody) {
        if (null == responseBody) {
            return null;
        }

        return ModelFactory.create(Order.class, responseBody.toString());
    }
}
