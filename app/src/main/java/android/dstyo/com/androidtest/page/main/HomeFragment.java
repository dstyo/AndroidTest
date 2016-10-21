package android.dstyo.com.androidtest.page.main;

import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.adapter.ViewPagerAdapter;
import android.dstyo.com.androidtest.base.BaseFragment;
import android.dstyo.com.androidtest.page.cars.CarsFragment;
import android.dstyo.com.androidtest.page.orders.OrdersFragment;
import android.dstyo.com.androidtest.page.users.UsersFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(toolbar, R.string.app_name);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        OrdersFragment ordersFragment = new OrdersFragment();
        CarsFragment carsFragment = new CarsFragment();
        UsersFragment usersFragment = new UsersFragment();
        adapter.addFragment(ordersFragment, getResources().getString(R.string.tab_orders));
        adapter.addFragment(carsFragment, getResources().getString(R.string.tab_cars));
        adapter.addFragment(usersFragment, getResources().getString(R.string.tab_users));
        viewPager.setAdapter(adapter);
    }

}
