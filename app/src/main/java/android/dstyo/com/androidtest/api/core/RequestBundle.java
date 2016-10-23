package android.dstyo.com.androidtest.api.core;

import android.dstyo.com.androidtest.api.handler.AbstractResponseHandler;

import com.loopj.android.http.RequestParams;

/**
 * Class that wrapping request parameter on restful client request.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class RequestBundle {
    private final String relativeUrl;
    private final RequestParams requestParams;
    private final AbstractResponseHandler responseHandler;
    private final boolean isPostRequest;

    public RequestBundle(String relativeUrl, RequestParams requestParams,
                         AbstractResponseHandler responseHandler, boolean isPostRequest) {
        this.relativeUrl = relativeUrl;
        this.requestParams = requestParams;
        this.responseHandler = responseHandler;
        this.isPostRequest = isPostRequest;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public AbstractResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public boolean isPostRequest() {
        return isPostRequest;
    }
}
