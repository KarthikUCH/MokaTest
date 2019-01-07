package moka.pos.test.network;

import java.util.List;

import io.reactivex.Observable;
import moka.pos.test.data.entity.Item;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface MokaApiService {

    @GET("/photos")
    Observable<List<Item>> getAllItems();

}
