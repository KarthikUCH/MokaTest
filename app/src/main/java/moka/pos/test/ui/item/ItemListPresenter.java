package moka.pos.test.ui.item;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import moka.pos.test.data.entity.Item;
import moka.pos.test.data.room.MokaDatabase;
import moka.pos.test.network.MokaApiService;
import moka.pos.test.network.RestServiceFactory;
import moka.pos.test.ui.base.BasePresenter;

/**
 * Created by karthikeyan on 23/1/18.
 */

public class ItemListPresenter<VI extends IItemListView> extends BasePresenter<VI> implements IItemListPresenter<VI> {

    private final RestServiceFactory mRestServiceFactory;
    //private final ItemsManager mItemsManager;
    private final MokaDatabase mMokaDatabase;
    private final CompositeDisposable mCompositeDisposable;

    public ItemListPresenter(RestServiceFactory mRestServiceFactory, MokaDatabase mokaDatabase) {
        this.mRestServiceFactory = mRestServiceFactory;
        this.mMokaDatabase = mokaDatabase;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachViewInteractor(VI view) {
        super.attachViewInteractor(view);
        getAllItems();
    }

    @Override
    public void getAllItems() {
        FetchAllItems();

        if (!getViewInteractor().isNetworkConnected()) {
            getViewInteractor().showToast("No network connection");
            return;
        }

        MokaApiService apiService = mRestServiceFactory.create(MokaApiService.class);

        apiService.getAllItems()
                .map(new Function<List<Item>, List<Item>>() {
                    @Override
                    public List<Item> apply(List<Item> items) throws Exception {
                        mMokaDatabase.itemDao().insertItems(items);
                        return mMokaDatabase.itemDao().getItems();
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
                        getViewInteractor().onAllItemsSuccess(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewInteractor().onAllItemsFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void FetchAllItems() {
        Observable.fromCallable(new Callable<List<Item>>() {
            @Override
            public List<Item> call() throws Exception {
                return mMokaDatabase.itemDao().getItems();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        getViewInteractor().onAllItemsSuccess(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewInteractor().onAllItemsFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void detachViewInteractor() {
        super.detachViewInteractor();
        mCompositeDisposable.clear();
    }
}
