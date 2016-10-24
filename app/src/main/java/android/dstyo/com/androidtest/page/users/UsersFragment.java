package android.dstyo.com.androidtest.page.users;


import android.dstyo.com.androidtest.R;
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
 * Representing Orders Fragment.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class UsersFragment extends Fragment {


    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        UsersListFragment usersListFragment = new UsersListFragment();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, usersListFragment)
                .commit();
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDialog();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_users, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addUserDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addUserDialog() {
        FragmentManager fragmentManager = getChildFragmentManager();
        UserAddFragment userAddFragment = new UserAddFragment();
        userAddFragment.show(fragmentManager, "tag");
    }


}
