package android.dstyo.com.androidtest.page.orders;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.dstyo.com.androidtest.R;

/**
 * Representing Orders Fragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class OrdersFragment extends Fragment {


    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_orders, menu);
    }

}
