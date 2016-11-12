package android.dstyo.com.androidtest.api.core;

import android.dstyo.com.androidtest.AndroidApplication;
import android.dstyo.com.androidtest.api.handler.GeneralResponseHandler;
import android.dstyo.com.androidtest.constant.DomainConstant;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

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
        JSONObject params = requestBundle.getJsonParams();
        RequestParams requestParams = requestBundle.getRequestParams();
        GeneralResponseHandler responseHandler = new GeneralResponseHandler(this, requestBundle, TAG);

        if (requestBundle.isPostRequest()) {

            if (requestParams != null) {
                return asyncHttpClient.post(
                        absoluteUrl,
                        requestParams,
                        responseHandler
                );
            }

            StringEntity entity = null;
            try {
                entity = new StringEntity(params.toString());
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            asyncHttpClient.addHeader("Accept", "text/json");
            asyncHttpClient.addHeader("content-type", "application/json");
            return asyncHttpClient.post(AndroidApplication.getContext(),
                    absoluteUrl, entity, "application/json", responseHandler);
        }

        if (requestBundle.isDeleteRequest()) {
            return asyncHttpClient.delete(
                    absoluteUrl,
                    responseHandler
            );
        }

        if (requestBundle.isPutRequest()) {
            return asyncHttpClient.put(
                    absoluteUrl,
                    requestParams,
                    responseHandler
            );
        }

        return asyncHttpClient.get(
                absoluteUrl,
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
