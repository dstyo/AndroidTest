package android.dstyo.com.androidtest.api.core;

import android.dstyo.com.androidtest.api.handler.GeneralResponseHandler;
import android.dstyo.com.androidtest.constant.DomainConstant;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * Restful client class to process request asynchronously.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class AsyncRestClient {
    private static final String TAG = "AsyncRestClient";

    private static final int DEFAULT_TIMEOUT = 20 * 1000;

    AsyncHttpClient asyncHttpClient;

    AsyncRestClient() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setConnectTimeout(DEFAULT_TIMEOUT);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return DomainConstant.API_BASE_URL + relativeUrl;
    }

    private RequestHandle getRequestHandle(RequestBundle requestBundle, Object TAG) {
        String absoluteUrl = getAbsoluteUrl(requestBundle.getRelativeUrl());
        RequestParams params = requestBundle.getRequestParams();
        GeneralResponseHandler responseHandler = new GeneralResponseHandler(this, requestBundle, TAG);
        if (requestBundle.isPostRequest()) {
            return asyncHttpClient.post(
                    absoluteUrl,
                    params,
                    responseHandler
            );
        }

//        if (requestBundle.isDeleteRequest()) {
//            return asyncHttpClient.delete(
//                    absoluteUrl,
//                    responseHandler
//            );
//        }

        return asyncHttpClient.get(
                absoluteUrl,
                params,
                responseHandler
        );
    }

    /**
     * Send http request to async http client.
     *
     * @param requestBundle request information bundle
     * @param TAG           object tag that send the request
     */
    void doRequest(RequestBundle requestBundle, Object TAG) {
        RequestHandle requestHandle = getRequestHandle(requestBundle, TAG);

        if (TAG != null) {
            requestHandle.setTag(TAG);
        }
    }
}
