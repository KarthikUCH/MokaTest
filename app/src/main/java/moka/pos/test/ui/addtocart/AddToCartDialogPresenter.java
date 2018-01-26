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
    public void attachView(V view) {
        super.attachView(view);

        mItemId = getMvpView().getItemId();
        mItemTitle = getMvpView().getItemTitle();
        mItemPrice = getMvpView().getItemPrice();

        getMvpView().setTitle(mItemTitle + " - $" + mItemPrice);
        mQuantity = getMvpView().getItemQuantity();
        getMvpView().setQuantity(mQuantity);
    }

    @Override
    public void onClickIncrement() {
        if (mQuantity < 1000) {
            mQuantity++;
            getMvpView().setQuantity(mQuantity);
        }
    }

    @Override
    public void onClickDecrement() {
        if (mQuantity > 1) {
            mQuantity--;
            getMvpView().setQuantity(mQuantity);
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
                double discount = getMvpView().getChosenDiscount();

                // Check if item already exists with same discount
                // if exists merge the mQuantity
                if (!getMvpView().isEdit()) {
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
                if (getMvpView().isEdit()) {
                    mShoppingCartManager.updateCartItem(item, getMvpView().getItemDiscount());
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
                        getMvpView().finishView();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().finishView();
                        getMvpView().addedToCart();
                    }
                });
    }

    @Override
    public void onClickCancel() {
        getMvpView().finishView();
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
