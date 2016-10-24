package android.dstyo.com.androidtest.adapter;

import android.content.Context;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.base.AbstractListAdapter;
import android.dstyo.com.androidtest.model.Order;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class OrderListAdapter extends AbstractListAdapter<Order> {

    /**
     * Constructing Base List Adapter.
     *
     * @param context  Android {@link Context}
     * @param itemList list of item
     */
    public OrderListAdapter(Context context, List<Order> itemList) {
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
                    inflate(R.layout.item_list_order, viewGroup, false);
            return new OrderViewHolder(itemView);
        }

        return super.onCreateViewHolder(viewGroup, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_PROGRESS) {
            return;
        }

        Order order = getItemAt(position);
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        String titleOrder = order.getUser().getName() + " | " + order.getCar().getModel();
        orderViewHolder.titleOrder.setText(titleOrder);
        orderViewHolder.statusOrder.setText(getStatus(order.getEnd_date()));
        orderViewHolder.orderListClickListener
                = (OrderListClickListener) getListClickListener();
    }

    private String getStatus(String orderEndDate) {
        String statusOrder = null;
        String dateFormat = "yyyy-MM-dd";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date endDate = sdf.parse(orderEndDate);
            Date todayDate = new Date();
            todayDate = sdf.parse(sdf.format(todayDate));

            if (endDate.after(todayDate) || endDate.equals(todayDate))
                statusOrder = "active";
            else
                statusOrder = "finished";
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return statusOrder;
    }

    /**
     * Used to communicating interaction between adapter and other fragment or activity.
     */
    public interface OrderListClickListener extends ListClickListener {
        /**
         * Called when more button (has three dots on the upper right) has been clicked.
         *
         * @param selectedIndex the item index on the list
         */
        void onActionClick(View view, int selectedIndex);

    }

    private class OrderViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView titleOrder;
        private final TextView statusOrder;
        protected OrderListClickListener orderListClickListener;

        public OrderViewHolder(View v) {
            super(v);
            titleOrder = (TextView) v.findViewById(R.id.tv_order_title);
            statusOrder = (TextView) v.findViewById(R.id.tv_order_status);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (null != orderListClickListener) {
                orderListClickListener.onActionClick(view, getAdapterPosition());
            }
        }
    }
}
