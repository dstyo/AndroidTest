package android.dstyo.com.androidtest.page.users;


import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.adapter.UsersListAdapter;
import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserListResponseHandler;
import android.dstyo.com.androidtest.api.request.UserRequest;
import android.dstyo.com.androidtest.base.AbstractListFragment;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.User;
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
            public void onMoreActionClick(View v, final User user) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_user, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_user_detele:
                                deleteUsers(user.getId());
                                return true;
                            case R.id.menu_user_detail:
                                detailUserDialog(user.getId());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();

            }
        };

        setListListener(userListClickListener);
    }

    private void detailUserDialog(int id) {
        FragmentManager fragmentManager = getChildFragmentManager();
        UserAddFragment userAddFragment = new UserAddFragment();
        Bundle propertyBundle = new Bundle();
        propertyBundle.putInt(RequestConstant.USER_ID, id);
        userAddFragment.setArguments(propertyBundle);
        userAddFragment.show(fragmentManager, "Users");
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
        getListUsers();
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
                        setLoadedItems(userList, userList.size());
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

    private void deleteUsers(int userId) {
        if (!isInternetPresent()) {
            setNoInternetConnection();
            return;
        }

        showProgressLoading("Deleting Users");
        (new UserRequest(this)).deleteUsers(
                userId,
                new BooleanResponseHandler() {
                    @Override
                    public void onSuccess(Boolean status) {
                        doLoadList();
                        hideProgressLoading();
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

    @Override
    public Object getTagRequest() {
        return TAG;
    }


}
