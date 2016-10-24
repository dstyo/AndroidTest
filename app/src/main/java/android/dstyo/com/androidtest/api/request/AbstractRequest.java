package android.dstyo.com.androidtest.api.request;

import android.dstyo.com.androidtest.api.core.AsyncRestClientManager;
import android.dstyo.com.androidtest.api.handler.AbstractResponseHandler;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;

import com.loopj.android.http.RequestParams;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public abstract class AbstractRequest {

    private final AsyncRestClientManager restClientManager;

    private final String apiPrefix;

    /**
     * Use this constructor if request class does not have similar prefix on each request.
     *
     * @param tagInterface  object that mark the async request
     */
    AbstractRequest(TagInterface tagInterface) {
        this("", tagInterface);
    }

    /**
     * Use this constructor if request class has sam url prefix.
     *
     * @param apiPrefix     relative url prefix
     * @param tagInterface  object that mark the async request
     */
    AbstractRequest(String apiPrefix, TagInterface tagInterface) {
        this.apiPrefix = apiPrefix;
        Object tagRequest = tagInterface.getTagRequest();
        restClientManager = new AsyncRestClientManager(tagRequest);
    }

    /**
     * Returns complete relative url request.
     *
     * @param relativeApi   postfix relative api url
     * @return complete relative url request
     */
    protected String getCompleteUrl(String relativeApi) {
        return getCompleteUrl(relativeApi, "");
    }

    /**
     * Returns complete relative url request.
     * Use this if url request require item id.
     *
     * @param relativeApi   postfix relative api url without item id
     * @param itemId        item id of request
     * @return complete relative url request
     */
    protected String getCompleteUrl(String relativeApi, String itemId) {
        itemId = (itemId == null) || (itemId.isEmpty()) ? "" : "/" + itemId;
        return apiPrefix + relativeApi + itemId;
    }

    /**
     * Send post request to async rest client manager.
     *
     * @param relativeUrl       complete relative url
     * @param params            http request params
     * @param responseHandler   response handler that handle the callback
     */
    protected void post(String relativeUrl, RequestParams params,
                        AbstractResponseHandler responseHandler) {
        restClientManager.post(
                relativeUrl,
                params,
                responseHandler
        );
    }

    /**
     * Send get request to async rest client manager.
     *
     * @param relativeUrl       complete relative url
     * @param params            http request params
     * @param responseHandler   response handler that handle the callback
     */
    protected void get(String relativeUrl, RequestParams params,
                       AbstractResponseHandler responseHandler) {
        restClientManager.get(
                relativeUrl,
                params,
                responseHandler
        );
    }

    /**
     * Send delete request to async rest client manager.
     *
     * @param relativeUrl       complete relative url
     * @param params            http request params
     * @param responseHandler   response handler that handle the callback
     */
    protected void delete(String relativeUrl, RequestParams params,
                       AbstractResponseHandler responseHandler) {
        restClientManager.delete(
                relativeUrl,
                params,
                responseHandler
        );
    }
}
