package android.dstyo.com.androidtest.base;

import android.dstyo.com.androidtest.R;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Representing abstract class of Fragment which use item list on its content.
 *
 * @param <T> the item type generic object
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public abstract class AbstractListFragment<T> extends AbstractAsyncRequestFragment {
    private static final String TAG = "AbstractListFragment";

    private static final String
            IS_LOADING = "is_loading",
            TOTAL_LOADED = "total_loaded",
            TOTAL_ITEMS = "total_items";

    private AbstractListAdapter<T> rvListAdapter;

    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewList;
    private RelativeLayout relativeLayoutErrorNoItem;
    private LinearLayoutManager linearLayoutManager;

    private boolean isLoading;
    private int totalLoaded;
    private int totalItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        totalLoaded = 0;
        totalItems = 0;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        isLoading = savedInstanceState.getBoolean(IS_LOADING, false);
        totalLoaded = savedInstanceState.getInt(TOTAL_LOADED);
        totalItems = savedInstanceState.getInt(TOTAL_ITEMS);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(IS_LOADING, isLoading);
        outState.putInt(TOTAL_LOADED, totalLoaded);
        outState.putInt(TOTAL_ITEMS, totalItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle) {
            loadBundle(bundle);
        }

        View view = inflater.inflate(R.layout.fragment_rv_list, container, false);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.content);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        recyclerViewList = (RecyclerView) view.findViewById(R.id.rv_list);
        relativeLayoutErrorNoItem = (RelativeLayout) view.findViewById(R.id.rl_error_no_item_found);
        setListAdapter();

        recyclerViewList.setAdapter(rvListAdapter);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        recyclerViewList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isLoading) {
                    return;
                }

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && totalLoaded < totalItems) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        Log.d(TAG, "Load from scroll");
                        doLoadList();
                    }
                }
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doLoadList();
            }
        });

        swipeRefreshLayout.setEnabled(true);

        setListListener();

        onBeforeCreateViewFinished();

        doInitLoadItems();

        return view;
    }

    @Override
    public void hideProgressLoading() {
        super.hideProgressLoading();

        hideLoading();
    }

    /**
     * Load the bundle to super class's member.
     * Called when <code>onCreateView</code> started.
     *
     * @param bundle Bundle to be passed
     */
    protected void loadBundle(Bundle bundle) {
    }

    /**
     * Set List Adapter to <code>AbstractListFragment</code>'s <code>rvListAdapter</code>.
     * Called inside <code>onCreateView</code>.
     */
    protected abstract void setListAdapter();

    /**
     * Set List Listener to <code>AbstractListFragment</code>'s <code>rvListAdapter</code>.
     * Called inside <code>onCreateView</code>.
     */
    protected abstract void setListListener();

    /**
     * Initialize item list.
     * Called after <code>onCreateView</code> finished.
     */
    protected abstract void doInitLoadItems();

    /**
     * Load item List from given page.
     *
     */
    protected void doLoadList() {
        if (null == rvListAdapter) {
            Log.d(TAG, "re-init adapter");
            setListAdapter();
        }
    }

    /**
     * Called before the view is displayed on screen.
     */
    protected void onBeforeCreateViewFinished() {
    }

    /**
     * Return CoordinationLayout object.
     *
     * @return CoordinationLayout
     */
    protected CoordinatorLayout getCoordinatorLayout() {
        return coordinatorLayout;
    }

    /**
     * Set List adapter from given <code>AbstractListAdapter</code>.
     *
     * @param abstractListAdapter the base list adapter
     */
    protected void setListAdapter(AbstractListAdapter<T> abstractListAdapter) {
        rvListAdapter = abstractListAdapter;
    }

    /**
     * Return current <code>AbstractListAdapter</code>.
     *
     * @return <code>AbstractListAdapter</code>
     */
    protected AbstractListAdapter getListAdapter() {
        return rvListAdapter;
    }

    /**
     * Get current <code>RecyclerView</code>.
     *
     * @return <code>RecyclerView</code>
     */
    protected RecyclerView getRecyclerViewList() {
        return recyclerViewList;
    }

    /**
     * Set list click listener.
     *
     * @param listClickListener the listener that called after the item was clicked
     */
    protected void setListListener(AbstractListAdapter.ListClickListener listClickListener) {
        if (null != listClickListener) {
            rvListAdapter.setOnItemClickListener(listClickListener);
        }
    }

    /**
     * Show loading progress from given page items.
     */
    protected void showLoading() {
        hideKeyboard();

        isLoading = true;

        if (totalItems == 0) {
            if (null != relativeLayoutErrorNoItem) {
                relativeLayoutErrorNoItem.setVisibility(View.GONE);
                rvListAdapter.clearList();

                if (swipeRefreshLayout.isRefreshing()) {
                    recyclerViewList.setVisibility(View.GONE);
                }

                rvListAdapter.addProgressBar();
            }
        }
    }

    /**
     * Hide loading progress.
     */
    protected void hideLoading() {
        isLoading = false;

        if (rvListAdapter != null) {
            rvListAdapter.removeProgressBar();
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

        if (recyclerViewList != null) {
            recyclerViewList.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set Loaded Item to recyclerView adapter.
     *
     * @param items      the collection of item list
     * @param totalItems the total count of collection items
     */
    protected void setLoadedItems(List<T> items, int totalItems) {
        Log.d(TAG, " size = " + items.size() + ", total items = " + totalItems);
        hideLoading();

        rvListAdapter.clearList();
        totalLoaded = 0;
        this.totalItems = totalItems;

        rvListAdapter.addList(items);
        totalLoaded += items.size();

        if (totalItems == 0) {
            setZeroLoadedItem();
        }

    }

    /**
     * Called when callback give <code>onFailure</code> response.
     */
    protected void setZeroLoadedItem() {
        hideLoading();

        if (null == rvListAdapter.getItemList() || rvListAdapter.getItemCount() == 0) {
            setHasNoItem();
        }
    }

    /**
     * Called when <code>setZeroLoadedItem</code> called and <code>totalItems = 0</code>.
     */
    protected void setHasNoItem() {
    }

}
