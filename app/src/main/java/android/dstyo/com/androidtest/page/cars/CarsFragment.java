package android.dstyo.com.androidtest.page.cars;


import android.app.Activity;
import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.page.users.UserAddFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Representing Cars Fragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class CarsFragment extends Fragment {


    public CarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        CarsListFragment carsListFragment = new CarsListFragment();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, carsListFragment)
                .commit();
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCarDialog();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cars, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addCarDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addCarDialog() {
        FragmentManager fragmentManager = getChildFragmentManager();
        CarsAddFragment carsAddFragment = new CarsAddFragment();
        carsAddFragment.setTargetFragment(
                CarsFragment.this, RequestConstant.ADD_CAR);
        carsAddFragment.show(fragmentManager, "Car");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestConstant.ADD_CAR) {
            if (resultCode == Activity.RESULT_OK) {
                CarsListFragment carsListFragment = new CarsListFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_content, carsListFragment)
                        .commit();
            }
        }
    }

}
