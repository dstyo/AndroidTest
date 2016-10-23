package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.handler.OrderListResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class CarRequest extends AbstractRequest {

    private static final String API_CARS_REQUEST = "/cars.json";

    public CarRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getCars(RequestParams requestParams, CarListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_CARS_REQUEST),
                requestParams,
                responseHandler
        );
    }
}
