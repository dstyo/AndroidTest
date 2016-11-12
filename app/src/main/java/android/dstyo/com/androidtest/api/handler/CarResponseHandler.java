package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.model.ModelFactory;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.12
 */
public class CarResponseHandler extends AbstractResponseHandler<Car> {
    @Override
    protected Car parseResponse(JSONArray responseBody) {
        return null;
    }

    @Override
    protected Car parseResponse(JSONObject responseBody) {
        if (null == responseBody) {
            return null;
        }

        return ModelFactory.create(Car.class, responseBody.toString());
    }
}
