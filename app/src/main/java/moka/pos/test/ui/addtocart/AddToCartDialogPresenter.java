package moka.pos.test.ui.addtocart;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moka.pos.test.data.ShoppingCartManager;
import moka.pos.test.data.model.CartItem;
import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 24/1/18.
 */

public class AddToCartDialogPresenter<V extends IAddToCartView> extends BasePresenter<V> implements IAddToCartPresenter<V> {

    private int mItemId;
    private String mItemTitle;
    private double mItemPrice;

    private int mQuantity = 0;

    private final ShoppingCartManager mShoppingCartManager;

    public AddToCartDialogPresenter(ShoppingCartManager mShoppingCartManager) {
        this.mShoppingCartManager = mShoppingCartManager;
    }

    @Override
    public void attachViewInteractor(V view) {
        super.attachViewInteractor(view);

        mItemId = getViewInteractor().getItemId();
        mItemTitle = getViewInteractor().getItemTitle();
        mItemPrice = getViewInteractor().getItemPrice();

        getViewInteractor().setTitle(mItemTitle + " - $" + mItemPrice);
        mQuantity = getViewInteractor().getItemQuantity();
        getViewInteractor().setQuantity(mQuantity);
    }

    @Override
    public void onClickIncrement() {
        if (mQuantity < 1000) {
            mQuantity++;
            getViewInteractor().setQuantity(mQuantity);
        }
    }

    @Override
    public void onClickDecrement() {
        if (mQuantity > 1) {
            mQuantity--;
            getViewInteractor().setQuantity(mQuantity);
        }
    }

    @Override
    public void onQuantityEdited(String quantity) {
        if (quantity.trim().length() == 0) {
            mQuantity = 0;
            onClickIncrement();
        } else {
            mQuantity = Integer.parseInt(quantity.trim());
        }
    }

    @Override
    public void onclickSave() {
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                double discount = getViewInteractor().getChosenDiscount();

                // Check if item already exists with same discount
                // if exists merge the mQuantity
                if (!getViewInteractor().isEdit()) {
                    CartItem existingCartItem = mShoppingCartManager.getItemFormCart(mItemId, discount);
                    if (existingCartItem != null) {
                        mQuantity += existingCartItem.getQuantity();
                    }
                }

                double totalPrice = mQuantity * mItemPrice;
                double discountRate =(totalPrice * discount) / 100;

                CartItem item = new CartItem(mItemId, mQuantity, discount);
                item.setTotalPrice(totalPrice);
                item.setDiscountRate(discountRate);
                if (getViewInteractor().isEdit()) {
                    mShoppingCartManager.updateCartItem(item, getViewInteractor().getItemDiscount());
                } else {
                    mShoppingCartManager.insertCartItem(item);
                }
                return true;
            }
        })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getViewInteractor().finishView();
                    }

                    @Override
                    public void onComplete() {
                        getViewInteractor().finishView();
                        getViewInteractor().addedToCart();
                    }
                });
    }

    @Override
    public void onClickCancel() {
        getViewInteractor().finishView();
    }

    @Override
    public void detachViewInteractor() {
        super.detachViewInteractor();
    }
}
