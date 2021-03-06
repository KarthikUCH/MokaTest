package moka.pos.test.ui.discounts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moka.pos.test.R;
import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.network.model.Discount;
import moka.pos.test.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscountListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscountListFragment extends BaseFragment implements IDiscountView {

    @BindView(R.id.recycleview_discounts)
    RecyclerView mRecyclerView;

    @Inject
    IDiscountPresenter mDiscountPresenter;

    private DiscountListAdapter mAdapter;

    public DiscountListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DiscountListFragment.
     */
    public static DiscountListFragment newInstance() {
        DiscountListFragment fragment = new DiscountListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount_list, container, false);

        ButterKnife.bind(this, view);

        setAdapter();
        mDiscountPresenter.attachView(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDiscountPresenter.detachView();
    }

    private void setAdapter() {
        mAdapter = new DiscountListAdapter(new ArrayList<Discount>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////             METHODS FROM @IDiscountView INTERFACE                  //////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void displayDiscounts(ArrayList<Discount> discountList) {
        mAdapter.swapData(discountList);
    }
}
