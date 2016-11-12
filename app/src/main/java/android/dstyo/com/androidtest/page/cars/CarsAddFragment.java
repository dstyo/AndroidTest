package android.dstyo.com.androidtest.page.cars;


import android.app.Activity;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.api.handler.CarResponseHandler;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.base.AbstractAsyncRequestDialogFragment;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.Car;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsAddFragment extends AbstractAsyncRequestDialogFragment {

    private TextInputLayout textInputBrand;
    private TextInputLayout textInputModel;
    private TextInputLayout textInputPlat;
    private TextInputLayout textInputFare;
    private Button btnSave;
    private int carId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.activity_add_car, container, false);
        textInputBrand = (TextInputLayout) view.findViewById(R.id.text_input_car_brand);
        textInputModel = (TextInputLayout) view.findViewById(R.id.text_input_car_model);
        textInputPlat = (TextInputLayout) view.findViewById(R.id.text_input_car_plat);
        textInputFare = (TextInputLayout) view.findViewById(R.id.text_input_car_fare);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    setParam(false);
                }
            }
        });

        if (bundle != null) {
            int carId = bundle.getInt(RequestConstant.CAR_ID);
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
        return view;
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
                        carAddNotify();
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

    private void carAddNotify() {
        hideProgressLoading();
        dismissAndNotifyTarget(
                RequestConstant.ADD_CAR,
                Activity.RESULT_OK
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
                        carAddNotify();
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
