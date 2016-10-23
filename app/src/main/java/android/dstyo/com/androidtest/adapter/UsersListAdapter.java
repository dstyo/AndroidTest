package android.dstyo.com.androidtest.adapter;

import android.content.Context;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.base.AbstractListAdapter;
import android.dstyo.com.androidtest.model.User;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class UsersListAdapter extends AbstractListAdapter<User> {

    /**
     * Constructing Base List Adapter.
     *
     * @param context  Android {@link Context}
     * @param itemList list of item
     */
    public UsersListAdapter(Context context, List<User> itemList) {
        super(context, itemList);
    }

    @Override
    public int getItemViewType(int position) {
        // get default type
        int viewType = super.getItemViewType(position);

        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.item_list_user, viewGroup, false);
            return new UsersViewHolder(itemView);
        }

        return super.onCreateViewHolder(viewGroup, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_PROGRESS) {
            return;
        }

        User user = getItemAt(position);
        UsersViewHolder userViewHolder = (UsersViewHolder) holder;
        userViewHolder.titleUser.setText(user.getName());
        userViewHolder.addressUser.setText(user.getAddress());
        userViewHolder.userListClickListener
                = (UserListClickListener) getListClickListener();
    }

    /**
     * Used to communicating interaction between adapter and other fragment or activity.
     */
    public interface UserListClickListener extends ListClickListener {
        /**
         * Called when more button (has three dots on the upper right) has been clicked.
         *
         * @param selectedIndex the item index on the list
         */
        void onMoreActionClick(View view, int selectedIndex);

    }

    private class UsersViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView titleUser;
        private final TextView addressUser;
        private final ImageView imgViewMoreMenu;
        protected UserListClickListener userListClickListener;

        public UsersViewHolder(View v) {
            super(v);

            titleUser = (TextView) v.findViewById(R.id.tv_title);
            addressUser = (TextView) v.findViewById(R.id.tv_status);
            imgViewMoreMenu = (ImageView) v.findViewById(R.id.iv_more_menu);
            imgViewMoreMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (null != userListClickListener) {
                userListClickListener.onMoreActionClick(view, getAdapterPosition());
            }
        }
    }
}
