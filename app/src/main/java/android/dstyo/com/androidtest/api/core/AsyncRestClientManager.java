package android.dstyo.com.androidtest.api.core;

import android.dstyo.com.androidtest.api.handler.AbstractResponseHandler;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class AsyncRestClientManager {

    private static AsyncRestClient restClient;
    static {
        restClient = new AsyncRestClient();
    }

    private final Object tagRequest;

    public AsyncRestClientManager(Object tagRequest) {
        this.tagRequest = tagRequest;
    }

    /**
     * Send html get method request to async http client.
     *
     * @param relativeUrl       relative url that come after domain prefix
     * @param params            http request parameter
     * @param responseHandler   response handler that handle the callback
     */
    public void get(String relativeUrl, JSONObject params,RequestParams requestParams,
                    AbstractResponseHandler responseHandler) {
        RequestBundle requestBundle = new RequestBundle(
                relativeUrl,
                params,
                requestParams,
                responseHandler,
                false,
                false,
                false
        );
        restClient.doRequest(requestBundle, tagRequest);
    }

    /**
     * Send html post method request to async http client.
     *
     * @param relativeUrl       relative url that come after domain prefix
     * @param params            http request parameter
     * @param responseHandler   response handler that handle the callback
     */
    public void post(String relativeUrl, JSONObject params,RequestParams requestParams,
                     AbstractResponseHandler responseHandler) {
        RequestBundle requestBundle = new RequestBundle(
                relativeUrl,
                params,
                requestParams,
                responseHandler,
                true,
                false,
                false
        );
        restClient.doRequest(requestBundle, tagRequest);
    }

    /**
     * Send html delete method request to async http client.
     *
     * @param relativeUrl       relative url that come after domain prefix
     * @param params            http request parameter
     * @param responseHandler   response handler that handle the callback
     */
    public void delete(String relativeUrl, JSONObject params,RequestParams requestParams,
                    AbstractResponseHandler responseHandler) {
        RequestBundle requestBundle = new RequestBundle(
                relativeUrl,
                params,
                requestParams,
                responseHandler,
                false,
                true,
                false
        );
        restClient.doRequest(requestBundle, tagRequest);
    }

    /**
     * Send html delete method request to async http client.
     *
     * @param relativeUrl       relative url that come after domain prefix
     * @param params            http request parameter
     * @param responseHandler   response handler that handle the callback
     */
    public void put(String relativeUrl, JSONObject params,RequestParams requestParams,
                       AbstractResponseHandler responseHandler) {
        RequestBundle requestBundle = new RequestBundle(
                relativeUrl,
                params,
                requestParams,
                responseHandler,
                false,
                false,
                true
        );
        restClient.doRequest(requestBundle, tagRequest);
    }

    /**
     * Cancel async http request that has been marked with tag.
     *
     * @param TAG   TAG that marked request which wants to be stopped
     */
    public static void cancelRequestsByTAG(Object TAG) {
        if (TAG == null) {
            return;
        }

        restClient.asyncHttpClient.cancelRequestsByTAG(TAG, true);
    }
}
