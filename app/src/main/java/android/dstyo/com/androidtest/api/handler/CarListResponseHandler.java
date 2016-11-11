package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.model.Car;
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
public class CarListResponseHandler extends AbstractResponseHandler<List<Car>> {

    @Override
    protected void onSuccess(JSONArray responseBody, List<Car> carList) {
        int totalItems = carList.size();
        onSuccess(carList,totalItems);
    }

    @Override
    protected List<Car> parseResponse(JSONArray responseBody) {
        if (null == responseBody) {
            return new ArrayList<>();
        }

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < responseBody.length(); i++) {
            String responseString = responseBody.optString(i);

            if (!responseString.equals("")) {
                Car car = ModelFactory.create(Car.class, responseString);
                carList.add(car);
            }
        }

        return carList;
    }

    @Override
    protected List<Car> parseResponse(JSONObject responseBody) {
        return null;
    }
}
