package moka.pos.test.ui.addtocart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import moka.pos.test.R;
import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.discounts.DiscountListAdapter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class ApplyDiscountListAdapter extends RecyclerView.Adapter<ApplyDiscountListAdapter.ViewHolder> {

    private final ArrayList<Discount> mDiscounts;
    private int mEnabledPosition;

    public ApplyDiscountListAdapter(ArrayList<Discount> mDiscounts, int enabledPosition) {
        this.mDiscounts = mDiscounts;
        this.mEnabledPosition = enabledPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_discount_apply, parent, false);
        return new ApplyDiscountListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Discount item = mDiscounts.get(position);
        holder.tvTitle.setText(item.getTitle() + " (" + item.getDiscount() + "%)");

        holder.switchDiscount.setOnCheckedChangeListener(null);

        holder.switchDiscount.setChecked(mEnabledPosition == position);

        holder.switchDiscount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEnabledPosition = position;
                } else {
                    mEnabledPosition = -1;
                }
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDiscounts.size();
    }

    public double getDiscount() {
        return mEnabledPosition == -1 ? 0 : mDiscounts.get(mEnabledPosition).getDiscount();
    }

    public void swapData(ArrayList<Discount> discountList) {
        mDiscounts.clear();
        mDiscounts.addAll(discountList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.switch_discount)
        Switch switchDiscount;

        public ViewHolder(View layoutView) {
            super(layoutView);
            ButterKnife.bind(this, layoutView);
        }
    }
}
