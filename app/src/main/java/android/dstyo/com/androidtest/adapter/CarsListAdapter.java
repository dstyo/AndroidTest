package android.dstyo.com.androidtest.adapter;

import android.content.Context;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.base.AbstractListAdapter;
import android.dstyo.com.androidtest.model.Car;
import android.dstyo.com.androidtest.util.CurrencyFormatter;
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
public class CarsListAdapter extends AbstractListAdapter<Car> {

    /**
     * Constructing Base List Adapter.
     *
     * @param context  Android {@link Context}
     * @param itemList list of item
     */
    public CarsListAdapter(Context context, List<Car> itemList) {
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
                    inflate(R.layout.item_list_car, viewGroup, false);
            return new CarViewHolder(itemView);
        }

        return super.onCreateViewHolder(viewGroup, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_PROGRESS) {
            return;
        }

        Car car = getItemAt(position);
        CarViewHolder carViewHolder = (CarViewHolder) holder;
        String titleOrder = car.getLicense_plat() + " | " + car.getModel();
        carViewHolder.car = car;
        carViewHolder.titleCar.setText(titleOrder);
        carViewHolder.amountCar.setText(CurrencyFormatter.format(car.getFarePerDay()));
        carViewHolder.statusCar.setText("available");
        carViewHolder.carListClickListener
                = (CarListClickListener) getListClickListener();
    }

    /**
     * Used to communicating interaction between adapter and other fragment or activity.
     */
    public interface CarListClickListener extends ListClickListener {
        /**
         * Called when more button (has three dots on the upper right) has been clicked.
         *
         * @param car the item on the list
         */
        void onMoreActionClick(View view, Car car);

    }

    private class CarViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Car car;
        private final TextView titleCar;
        private final TextView statusCar;
        private final ImageView imgViewMoreMenu;
        private final TextView amountCar;
        protected CarListClickListener carListClickListener;

        public CarViewHolder(View v) {
            super(v);

            titleCar = (TextView) v.findViewById(R.id.tv_title);
            statusCar = (TextView) v.findViewById(R.id.tv_status);
            amountCar = (TextView) v.findViewById(R.id.tv_amount);
            imgViewMoreMenu = (ImageView) v.findViewById(R.id.iv_more_menu);
            imgViewMoreMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (null != carListClickListener) {
                carListClickListener.onMoreActionClick(view, car);
            }
        }
    }


}
