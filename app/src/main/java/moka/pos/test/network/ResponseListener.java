package moka.pos.test.network;

/**
 * Created by karthikeyan on 23/1/18.
 */

public interface ResponseListener<T> {
    /**
     * Fired when request is successful.
     *
     * @param response result.
     */
    void onResponse(T response);

    /**
     * Fired when request is failed.
     *
     * @param errorMessage error message or null.
     */
    void onError(String errorMessage);
}
