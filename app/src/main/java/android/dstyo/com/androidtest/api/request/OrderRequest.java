package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.OrderListResponseHandler;
import android.dstyo.com.androidtest.api.handler.OrderResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class OrderRequest extends AbstractRequest {

    private static final String API_ORDER_REQUEST = "/orders";
    private static final String EXT_ORDER_REQUEST = ".json";
    private static final String ACT_ORDER_REQUEST = "/orders/";

    public OrderRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getOrders(JSONObject requestParams, OrderListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_ORDER_REQUEST + EXT_ORDER_REQUEST),
                requestParams,
                null,
                responseHandler
        );
    }

    public void getDetailOrders(int id, OrderResponseHandler responseHandler) {
        get(
                getCompleteUrl(ACT_ORDER_REQUEST + id + EXT_ORDER_REQUEST),
                null,
                null,
                responseHandler
        );
    }

    public void addOrder(JSONObject jsonObject, BooleanResponseHandler responseHandler) {
        post(getCompleteUrl(API_ORDER_REQUEST + EXT_ORDER_REQUEST),
                jsonObject,
                null,
                responseHandler);
    }
}
