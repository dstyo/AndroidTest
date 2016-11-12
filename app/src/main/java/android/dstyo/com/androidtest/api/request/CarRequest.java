package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.handler.CarResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class CarRequest extends AbstractRequest {

    private static final String API_CARS_REQUEST = "/cars";
    private static final String EXT_CARS_REQUEST = ".json";
    private static final String ACT_CARS_REQUEST = "/cars/";
    private static final String API_ORDERS_REQUEST = "/orders/new";


    public CarRequest(TagInterface tagInterface) {
        super("", tagInterface);
    }

    public void getCars(JSONObject requestParams, CarListResponseHandler responseHandler) {
        get(
                getCompleteUrl(API_CARS_REQUEST + EXT_CARS_REQUEST),
                requestParams,
                null,
                responseHandler
        );
    }

    public void deleteCars(int id, BooleanResponseHandler responseHandler) {
        delete(getCompleteUrl(ACT_CARS_REQUEST + id + EXT_CARS_REQUEST),
                null,
                null,
                responseHandler);
    }


    public void addCar(RequestParams requestParams, CarResponseHandler responseHandler) {
        post(getCompleteUrl(API_CARS_REQUEST + EXT_CARS_REQUEST),
                null,
                requestParams,
                responseHandler);
    }

    public void updateCar(int id, RequestParams requestParams, CarResponseHandler responseHandler) {
        put(getCompleteUrl(ACT_CARS_REQUEST + id + EXT_CARS_REQUEST),
                null,
                requestParams,
                responseHandler);
    }

    public void orderCars(JSONObject requestParams, CarListResponseHandler responseHandler) {
        post(
                getCompleteUrl(API_ORDERS_REQUEST),
                requestParams,
                null,
                responseHandler
        );
    }

    public void getDetailCars(int id, CarResponseHandler responseHandler) {
        get(
                getCompleteUrl(ACT_CARS_REQUEST + id + EXT_CARS_REQUEST),
                null,
                null,
                responseHandler
        );
    }
}
