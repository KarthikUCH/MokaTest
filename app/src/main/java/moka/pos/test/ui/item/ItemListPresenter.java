package moka.pos.test.ui.item;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import moka.pos.test.data.ItemsManager;
import moka.pos.test.network.MokaApiService;
import moka.pos.test.network.RestServiceFactory;
import moka.pos.test.network.model.Item;
import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 23/1/18.
 */

public class ItemListPresenter<V extends IItemListView> extends BasePresenter<V> implements IItemListPresenter<V> {

    private final RestServiceFactory mRestServiceFactory;
    private final ItemsManager mItemsManager;
    private final CompositeDisposable mCompositeDisposable;

    public ItemListPresenter(RestServiceFactory mRestServiceFactory, ItemsManager itemsManager) {
        this.mRestServiceFactory = mRestServiceFactory;
        this.mItemsManager = itemsManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        getAllItems();
    }

    @Override
    public void getAllItems() {

        MokaApiService apiService = mRestServiceFactory.create(MokaApiService.class);

        apiService.getAllItems()
                .map(new Function<List<Item>, List<Item>>() {
                    @Override
                    public List<Item> apply(List<Item> items) throws Exception {
                        mItemsManager.insertItems(items);
                        return mItemsManager.getItems();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        getMvpView().onAllItemsSuccess(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().onAllItemsFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void detachView() {
        super.detachView();
        mCompositeDisposable.clear();
    }
}
