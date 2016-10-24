package android.dstyo.com.androidtest.page.orders;


import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.adapter.OrderListAdapter;
import android.dstyo.com.androidtest.api.handler.OrderListResponseHandler;
import android.dstyo.com.androidtest.api.request.OrderRequest;
import android.dstyo.com.androidtest.base.AbstractListFragment;
import android.dstyo.com.androidtest.model.Order;
import android.dstyo.com.androidtest.page.cars.OrderCarsActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing Orders Fragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class OrdersListFragment extends AbstractListFragment<Order> {

    private static final String TAG = "OrderListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void setListAdapter() {
        setListAdapter(new OrderListAdapter(getActivity(), new ArrayList<Order>()));
    }

    @Override
    protected void setListListener() {
        OrderListAdapter.OrderListClickListener orderListClickListener =
                new OrderListAdapter.OrderListClickListener() {
            @Override
            public void onActionClick(View view, int selectedIndex) {
                Intent intent = new Intent(getContext(), OrderCarsActivity.class);
                getContext().startActivity(intent);
            }
        };
        setListListener(orderListClickListener);
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
        getListOrders();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_orders, menu);
    }

    private void getListOrders() {
        RequestParams requestParams = new RequestParams();
        (new OrderRequest(this)).getOrders(
                requestParams,
                new OrderListResponseHandler() {
                    @Override
                    public void onSuccess(List<Order> orderList) {
                        setLoadedItems(orderList, orderList.size());
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {
                        setZeroLoadedItem();
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
