package android.dstyo.com.androidtest.page.users;


import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.adapter.CarsListAdapter;
import android.dstyo.com.androidtest.adapter.UsersListAdapter;
import android.dstyo.com.androidtest.api.handler.CarListResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserListResponseHandler;
import android.dstyo.com.androidtest.api.request.CarRequest;
import android.dstyo.com.androidtest.api.request.UserRequest;
import android.dstyo.com.androidtest.base.AbstractListFragment;
import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.model.User;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class UsersListFragment extends AbstractListFragment<User> {

    private static final String TAG = "OrderListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    protected void setListAdapter() {
        setListAdapter(new UsersListAdapter(getActivity(), new ArrayList<User>()));
    }

    @Override
    protected void setListListener() {
        UsersListAdapter.UserListClickListener userListClickListener
                = new UsersListAdapter.UserListClickListener() {

            @Override
            public void onMoreActionClick(View v, int selectedIndex) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_user, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onOptionsItemSelected(item);
                        return true;
                    }
                });
                popupMenu.show();

            }
        };

        setListListener(userListClickListener);
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

    private void getListOrders() {
        RequestParams requestParams = new RequestParams();
        (new UserRequest(this)).getUsers(
                requestParams,
                new UserListResponseHandler() {
                    @Override
                    public void onSuccess(List<User> userList) {
                        setLoadedItems(userList, userList.size());
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
