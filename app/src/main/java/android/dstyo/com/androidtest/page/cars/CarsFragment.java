package android.dstyo.com.androidtest.page.cars;


import android.content.Intent;
import android.dstyo.com.androidtest.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
        loadFragment();
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
        Intent intent = new Intent(getActivity(), CarsAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment();
    }

    private void loadFragment() {
        CarsListFragment carsListFragment = new CarsListFragment();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, carsListFragment)
                .commit();
    }
}
