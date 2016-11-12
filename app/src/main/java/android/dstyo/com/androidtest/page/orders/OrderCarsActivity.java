package android.dstyo.com.androidtest.page.orders;

import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.CarResponseHandler;
import android.dstyo.com.androidtest.api.handler.OrderResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserListResponseHandler;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.api.request.OrderRequest;
import android.dstyo.com.androidtest.api.request.UserRequest;
import android.dstyo.com.androidtest.base.AbstractAsyncRequestActivity;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.model.Order;
import android.dstyo.com.androidtest.model.User;
import android.dstyo.com.androidtest.util.CurrencyFormatter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCarsActivity extends AbstractAsyncRequestActivity {

    private final String TAG = "OrderCars";
    private int orderId;
    private TextInputLayout textInputStartDate;
    private TextInputLayout textInputEndDate;
    private Button btnConfirm;
    private TextView textViewName;
    private TextView textViewAmout;
    private TextView textViewStatus;
    private int carId, userId;
    private AutoCompleteTextView autoUsers;

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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        orderId = bundle.getInt(RequestConstant.ORDER_ID, -1);

        carId = bundle.getInt(RequestConstant.CAR_ID, -1);


        textInputStartDate = (TextInputLayout) findViewById(R.id.text_input_order_start_date);
        textInputEndDate = (TextInputLayout) findViewById(R.id.text_input_order_end_date);
        textViewName = (TextView) findViewById(R.id.tv_title);
        textViewStatus = (TextView) findViewById(R.id.tv_status);
        textViewAmout = (TextView) findViewById(R.id.tv_amount);
        autoUsers = (AutoCompleteTextView) findViewById(R.id.auto_form_user);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setParam();
            }
        });

        initToolbar();
        if (orderId != -1) {
            getDetailOrderCars();
        }
        else {
            getDetailCar(carId);
            getListUsers();
        }
    }

    private void setParam() {
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("start_date", textInputStartDate.getEditText().getText());
            jsonParams.put("end_date", textInputEndDate.getEditText().getText());
            jsonParams.put("car_id", carId);
            jsonParams.put("user_id", userId);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        addOrder(jsonParams);
    }

    private void addOrder(JSONObject jsonParams) {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        showProgressLoading("Adding Orders...");

        (new OrderRequest(this)).addOrder(
                jsonParams,
                new BooleanResponseHandler() {
                    @Override
                    public void onSuccess(Boolean status) {
                        hideProgressLoading();
                        returnIntentOK();
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {
                        hideProgressLoading();
                    }

                    @Override
                    public void onRequestTimedOut() {
                        setInternetTimedOut();
                    }
                }
        );
    }

    private void getDetailOrderCars() {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }
        autoUsers.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);
        showProgressLoading("Get Detail Orders ..");
        (new OrderRequest(this)).getDetailOrders(
                orderId,
                new OrderResponseHandler() {
                    @Override
                    public void onSuccess(Order order) {
                        hideProgressLoading();
                        setDetailOrder(order);
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {
                        // setZeroLoadedItem();
                    }

                    @Override
                    public void onRequestTimedOut() {
                        setInternetTimedOut();
                    }
                }
        );
    }

    private void getDetailCar(int carId) {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        (new CarRequest(this)).getDetailCars(
                carId,
                new CarResponseHandler() {
                    @Override
                    public void onSuccess(Car car) {
                        setDetailCar(car);
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {

                    }

                    @Override
                    public void onRequestTimedOut() {
                        setInternetTimedOut();
                    }
                }
        );
    }

    private void getListUsers() {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        (new UserRequest(this)).getUsers(
                null,
                new UserListResponseHandler() {
                    @Override
                    public void onSuccess(List<User> userList) {
                        setSpinnerUsers(userList);
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {

                    }

                    @Override
                    public void onRequestTimedOut() {
                        setInternetTimedOut();
                    }
                }
        );
    }

    private void setSpinnerUsers(final List<User> userList) {
        List<String> userListName = new ArrayList<>();
        final Map<String, Integer> map = new HashMap<>();
        for (User user : userList) {
            userListName.add(user.getName());
            map.put(user.getName(), user.getId());
        }

        ArrayAdapter<String> adapterUser = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, userListName);
        adapterUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoUsers.setAdapter(adapterUser);
        autoUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                userId = map.get(selectedItem);
            }
        });
    }

    private void setDetailCar(Car car) {
        String titleOrder = car.getLicense_plat() + " | " + car.getModel();
        textViewName.setText(titleOrder);
        textViewAmout.setText(CurrencyFormatter.format(car.getFarePerDay()));
        textViewStatus.setText("available");
    }

    private void setDetailOrder(Order order) {
        textInputStartDate.getEditText().setEnabled(false);
        textInputEndDate.getEditText().setEnabled(false);
        textInputStartDate.getEditText().setText(order.getStart_date());
        textInputEndDate.getEditText().setText(order.getEnd_date());
        getDetailCar(order.getCar_id());
    }

    @Override
    public Object getTagRequest() {
        return TAG;
    }
}
