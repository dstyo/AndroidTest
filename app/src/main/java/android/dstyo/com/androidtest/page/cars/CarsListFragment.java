package android.dstyo.com.androidtest.page.cars;


import android.app.Activity;
import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.adapter.CarsListAdapter;
import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.base.AbstractListFragment;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.page.orders.OrderCarsActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing Orders Fragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class CarsListFragment extends AbstractListFragment<Car> {

    private static final String TAG = "CarListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setListAdapter() {
        setListAdapter(new CarsListAdapter(getActivity(), new ArrayList<Car>()));
    }

    @Override
    protected void setListListener() {
        CarsListAdapter.CarListClickListener carListClickListener
                = new CarsListAdapter.CarListClickListener() {

            @Override
            public void onMoreActionClick(View v, final Car car) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_car, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_car_detele:
                                deleteCars(car.getId());
                                return true;
                            case R.id.menu_car_order:
                                intent = new Intent(getContext(), OrderCarsActivity.class);
                                intent.putExtra(RequestConstant.CAR_ID, car.getId());
                                startActivity(intent);
                                return true;
                            case R.id.menu_car_update:
                                updateCar(car.getId());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();

            }
        };

        setListListener(carListClickListener);
    }

    private void updateCar(int id){
        FragmentManager fragmentManager = getChildFragmentManager();
        CarsAddFragment userAddFragment = new CarsAddFragment();
        Bundle propertyBundle = new Bundle();
        propertyBundle.putInt(RequestConstant.CAR_ID, id);
        userAddFragment.setArguments(propertyBundle);
        userAddFragment.setTargetFragment(
                CarsListFragment.this, RequestConstant.ADD_CAR);
        userAddFragment.show(fragmentManager, "Cars");
    }

    @Override
    protected void doInitLoadItems() {
        if (getListAdapter().getItemCount() == 0) {
            doLoadList();
        }
    }

    @Override
    protected void doLoadList() {
        showLoading();
        getListCars();
    }

    private void getListCars() {
        (new CarRequest(this)).getCars(
                null,
                new CarListResponseHandler() {
                    @Override
                    public void onSuccess(List<Car> carList) {
                        setLoadedItems(carList, carList.size());
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {
                        setZeroLoadedItem();
                    }

                    @Override
                    public void onRequestTimedOut() {
                        setInternetTimedOut();
                    }
                }
        );
    }

    private void deleteCars(int carId) {
        showProgressLoading("Deleting Cars");
        (new CarRequest(this)).deleteCars(
                carId,
                new BooleanResponseHandler() {
                    @Override
                    public void onSuccess(Boolean status) {
                        doLoadList();
                        hideProgressLoading();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestConstant.ADD_CAR) {
            if (resultCode == Activity.RESULT_OK) {
                doLoadList();
            }
        }
    }

    @Override
    public Object getTagRequest() {
        return TAG;
    }


}
