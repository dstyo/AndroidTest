package android.dstyo.com.androidtest.page.cars;

import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.api.handler.CarResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.base.BaseAppCompactActivity;
import android.dstyo.com.androidtest.base.BaseFragment;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.Car;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


public class AddCarActivity extends BaseAppCompactActivity implements TagInterface {

    private TextInputLayout textInputBrand;
    private TextInputLayout textInputModel;
    private TextInputLayout textInputPlat;
    private TextInputLayout textInputFare;
    private Button btnSave;
    private int carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        textInputBrand = (TextInputLayout) findViewById(R.id.text_input_car_brand);
        textInputModel = (TextInputLayout) findViewById(R.id.text_input_car_model);
        textInputPlat = (TextInputLayout) findViewById(R.id.text_input_car_plat);
        textInputFare = (TextInputLayout) findViewById(R.id.text_input_car_fare);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    setParam(false);
                }
            }
        });

        if (bundle != null) {
            int carId = intent.getIntExtra(RequestConstant.CAR_ID, 1);
            getDetailCar(carId);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validateForm()) {
                        setParam(true);
                    }
                }
            });
        }
    }

    private void setParam(boolean isEdited) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("car[brand]", textInputBrand.getEditText().getText());
        requestParams.put("car[model]", textInputModel.getEditText().getText());
        requestParams.put("car[license_plat]", textInputPlat.getEditText().getText());
        requestParams.put("car[fare]", textInputFare.getEditText().getText());
        if (isEdited) {
            updateCar(requestParams);
        }
        else {
            addCar(requestParams);
        }
    }

    private void getDetailCar(int carId) {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        showProgressLoading("Get Detail Cars ...");
        (new CarRequest(this)).getDetailCars(
                carId,
                new CarResponseHandler() {
                    @Override
                    public void onSuccess(Car car) {
                        hideProgressLoading();
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

    private void setDetailCar(Car car) {
        textInputBrand.getEditText().setText(car.getBrand());
        textInputModel.getEditText().setText(car.getModel());
        textInputPlat.getEditText().setText(car.getLicense_plat());
        textInputFare.getEditText().setText(String.valueOf(car.getFarePerDay()));
        carId = car.getId();
    }

    private void addCar(RequestParams requestParams) {

        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        showProgressLoading("Adding cars...");

        (new CarRequest(this)).addCar(
                requestParams,
                new CarResponseHandler() {
                    @Override
                    public void onSuccess(Car car) {
                        hideProgressLoading();
                        returnIntentOK();
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

    private void updateCar(RequestParams requestParams) {

        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        showProgressLoading("Updating cars...");

        (new CarRequest(this)).updateCar(
                carId,
                requestParams,
                new CarResponseHandler() {
                    @Override
                    public void onSuccess(Car car) {
                        hideProgressLoading();
                        returnIntentOK();
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

    private boolean validateForm() {
        resetError();
        View requestFocusView = null;

        if (TextUtils.isEmpty(textInputBrand.getEditText().getText())) {
            textInputBrand.setError("Brand cannot be empty");
            requestFocusView = setRequestView(requestFocusView, textInputBrand);
        }
        if (TextUtils.isEmpty(textInputModel.getEditText().getText())) {
            textInputModel.setError("Model Phone cannot be empty");
            requestFocusView = setRequestView(requestFocusView, textInputModel);
        }
        if (TextUtils.isEmpty(textInputPlat.getEditText().getText())) {
            textInputPlat.setError("Plat cannot be empty");
            requestFocusView = setRequestView(requestFocusView, textInputPlat);
        }
        if (TextUtils.isEmpty(textInputFare.getEditText().getText())) {
            textInputFare.setError("Fare cannot be empty");
            requestFocusView = setRequestView(requestFocusView, textInputFare);
        }

        if (null != requestFocusView) {
            requestFocusView.requestFocus();
            return false;
        }

        return true;
    }

    private void resetError() {
        textInputBrand.setError(null);
        textInputModel.setError(null);
        textInputPlat.setError(null);
        textInputFare.setError(null);
    }

    private View setRequestView(View requestViewHolder, View requestView) {
        return null == requestViewHolder ? requestView : requestViewHolder;
    }

    @Override
    public Object getTagRequest() {
        return null;
    }
}
