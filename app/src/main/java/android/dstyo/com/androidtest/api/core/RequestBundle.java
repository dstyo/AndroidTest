package android.dstyo.com.androidtest.api.core;

import android.dstyo.com.androidtest.api.handler.AbstractResponseHandler;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * Class that wrapping request parameter on restful client request.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class RequestBundle {
    private final String relativeUrl;
    private final JSONObject requestParams;
    private final AbstractResponseHandler responseHandler;
    private final boolean isPostRequest;
    private final boolean isDeleteRequest;
    private final boolean isPutRequest;

    public RequestBundle(String relativeUrl, JSONObject requestParams,
                         AbstractResponseHandler responseHandler, boolean isPostRequest,
                         boolean isDeleteRequest, boolean isPutRequest) {
        this.relativeUrl = relativeUrl;
        this.requestParams = requestParams;
        this.responseHandler = responseHandler;
        this.isPostRequest = isPostRequest;
        this.isDeleteRequest = isDeleteRequest;
        this.isPutRequest = isPutRequest;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public JSONObject getRequestParams() {
        return requestParams;
    }

    public AbstractResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public boolean isPostRequest() {
        return isPostRequest;
    }

    public boolean isDeleteRequest() {
        return isDeleteRequest;
    }

    public boolean isPutRequest() {
        return isPutRequest;
    }
}
