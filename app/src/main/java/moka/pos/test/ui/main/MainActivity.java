package moka.pos.test.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moka.pos.test.R;
import moka.pos.test.application.ApplicationComponent;
import moka.pos.test.data.model.CartItem;
import moka.pos.test.network.model.Item;
import moka.pos.test.ui.addtocart.AddToCartDialogFragment;
import moka.pos.test.ui.base.BaseVMActivity;
import moka.pos.test.ui.discounts.DiscountListFragment;
import moka.pos.test.ui.item.ItemListFragment;
import moka.pos.test.ui.library.LibraryFragment;
import moka.pos.test.ui.shoppingcart.ShoppingCartFragment;

public class MainActivity extends BaseVMActivity<MainViewModel> implements ItemListFragment.OnAllItemClickListener,
        LibraryFragment.OnLibraryInteractionListener, ShoppingCartFragment.OnCartItemInteractionListener,
        AddToCartDialogFragment.OnItemAddedToCartListener {

    private MainViewModel mMainViewModel;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.tv_lib_title)
    TextView tvLibTitle;

    @Inject
    IMainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mMainPresenter.attachViewInteractor(mMainViewModel);

        displayLibrary();
        displayShoppingCart();
    }


    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    protected void subscribeViewModel() {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final Observer<MainViewModel.ToDoStatus> mainVmDataObserver = new Observer<MainViewModel.ToDoStatus>() {
            @Override
            public void onChanged(@Nullable MainViewModel.ToDoStatus toDoStatus) {
                switch (toDoStatus) {
                    case DISPLAY_LIBRARY:
                        displayLibrary();
                        break;
                    case DISPLAY_ALL_ITEMS:
                        displayAllItems();
                        break;
                    case DISPLAY_DISCOUNT_LIST:
                        displayDiscountList();
                        break;
                }

            }
        };

        mMainViewModel.getToDoStatus().observe(this, mainVmDataObserver);

        super.subscribeViewModel();
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.img_back)
    public void onClickTooBarBack(View view) {
        mMainPresenter.onClickToolbarBack();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////                  METHODS FROM @IMainView INTERFACE                 //////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void displayLibrary() {
        imgBack.setVisibility(View.GONE);
        tvLibTitle.setText(getString(R.string.text_title_library));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_one, LibraryFragment.newInstance(), LibraryFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void displayAllItems() {
        imgBack.setVisibility(View.VISIBLE);
        tvLibTitle.setText(getString(R.string.text_title_all_items));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_one, ItemListFragment.newInstance(), ItemListFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void displayDiscountList() {
        imgBack.setVisibility(View.VISIBLE);
        tvLibTitle.setText(getString(R.string.text_title_discounts));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_one, DiscountListFragment.newInstance(), DiscountListFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void displayShoppingCart() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_two, ShoppingCartFragment.newInstance(), ShoppingCartFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void displayAddToCartDialog(int itemId, String title, double price, int quantity, double discount, boolean editFlag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(AddToCartDialogFragment.class.getName());
        if (prevFragment != null) {
            fragmentTransaction.remove(prevFragment);
        }
        fragmentTransaction.addToBackStack(null);
        AddToCartDialogFragment.newInstance(itemId, title, price, quantity, discount, editFlag).show(fragmentManager, AddToCartDialogFragment.class.getName());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////     METHODS FROM @ItemListFragment#OnAllItemClickListener INTERFACE        //////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onListFragmentInteraction(Item item) {
        displayAddToCartDialog(item.getId(), item.getTitle(), item.getPrice(), 1, 0, false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////    METHODS FROM @LibraryFragment#OnLibraryInteractionListener INTERFACE      /////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onLibraryFragmentInteraction(int clickedItem) {
        switch (clickedItem) {
            case LibraryFragment.ON_CLICK_DISCOUNTS:
                mMainPresenter.onClickDiscounts();
                break;
            case LibraryFragment.ON_CLICK_ALL_ITEMS:
                mMainPresenter.onClickAllItems();
                break;

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////    METHODS FROM @ShoppingCartFragment#OnCartItemInteractionListener INTERFACE     //////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void editCartItem(CartItem item) {
        displayAddToCartDialog(item.getItemId(), item.getItemTitle(), item.getTotalPrice() / item.getQuantity()
                , item.getQuantity(), item.getDiscount(), true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////    METHODS FROM @AddToCartDialogFragment#OnItemAddedToCartListener INTERFACE      //////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onItemAdded() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ShoppingCartFragment cartFragment = (ShoppingCartFragment) fragmentManager.findFragmentByTag(ShoppingCartFragment.class.getName());
        if (cartFragment != null) {
            cartFragment.refreshCartList();
        }

    }
}
