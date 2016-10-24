package android.dstyo.com.androidtest.page.cars;

import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.base.AbstractAsyncRequestActivity;
import android.dstyo.com.androidtest.model.Car;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

public class OrderCarsActivity extends AbstractAsyncRequestActivity {

    private final String TAG = "OrderCars";

    /**
     * Initialize AppCompat Toolbar.
     * Call this after {@link AppCompatActivity#setContentView(int)}.
     */
    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.order_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cars);
        initToolbar();
        orderCars();
    }

    private void orderCars() {
        RequestParams requestParams = new RequestParams();
        (new CarRequest(this)).orderCars(
                requestParams,
                new CarListResponseHandler() {
                    @Override
                    public void onSuccess(List<Car> carList) {
                        //setLoadedItems(carList, carList.size());
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {
                        // setZeroLoadedItem();
                    }

                    @Override
                    public void onRequestTimedOut() {
                        //setInternetTimedOut(getCoordinatorLayout());
                    }
                }
        );
    }

    @Override
    public Object getTagRequest() {
        return TAG;
    }
}
