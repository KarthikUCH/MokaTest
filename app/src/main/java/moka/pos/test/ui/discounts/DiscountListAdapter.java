package moka.pos.test.ui.discounts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import moka.pos.test.R;
import moka.pos.test.network.model.Discount;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class DiscountListAdapter extends RecyclerView.Adapter<DiscountListAdapter.ViewHolder> {

    private final ArrayList<Discount> mDiscounts;

    public DiscountListAdapter(ArrayList<Discount> mDiscounts) {
        this.mDiscounts = mDiscounts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_discount, parent, false);
        return new DiscountListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Discount item = mDiscounts.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvPrice.setText(item.getDiscount()+" %");
    }

    @Override
    public int getItemCount() {
        return mDiscounts.size();
    }

    public void swapData(ArrayList<Discount> discountList) {
        mDiscounts.clear();
        mDiscounts.addAll(discountList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_discount)
        TextView tvPrice;

        public ViewHolder(View layoutView) {
            super(layoutView);
            ButterKnife.bind(this, layoutView);
        }
    }

}
