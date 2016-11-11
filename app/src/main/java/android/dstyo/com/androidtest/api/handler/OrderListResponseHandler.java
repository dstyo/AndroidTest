package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.ModelFactory;
import android.dstyo.com.androidtest.model.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class OrderListResponseHandler extends AbstractResponseHandler<List<Order>> {

    @Override
    protected void onSuccess(JSONArray responseBody, List<Order> orderList) {
        int totalItems = orderList.size();
        onSuccess(orderList,totalItems);
    }

    @Override
    protected List<Order> parseResponse(JSONArray responseBody) {
        if (null == responseBody) {
            return new ArrayList<>();
        }

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < responseBody.length(); i++) {
            String responseString = responseBody.optString(i);

            if (!responseString.equals("")) {
                Order order = ModelFactory.create(Order.class, responseString);
                orderList.add(order);
            }
        }

        return orderList;
    }

    @Override
    protected List<Order> parseResponse(JSONObject responseBody) {
        return null;
    }
}
