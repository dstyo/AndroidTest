package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.OrderListResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class OrderRequest extends AbstractRequest {

    private static final String API_ORDER_REQUEST = "/orders.json";

    public OrderRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getOrders(JSONObject requestParams, OrderListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_ORDER_REQUEST),
                requestParams,
                null,
                responseHandler
        );
    }
}
