package android.dstyo.com.androidtest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing View Pager Adapter.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.21
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<FragmentItem> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(new FragmentItem(fragment, title));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList.get(position).fragmentTitle;
    }

    private static class FragmentItem {
        protected Fragment fragment;
        protected String fragmentTitle;

        public FragmentItem(Fragment fragment, String fragmentTitle) {
            this.fragment = fragment;
            this.fragmentTitle = fragmentTitle;
        }
    }
}
