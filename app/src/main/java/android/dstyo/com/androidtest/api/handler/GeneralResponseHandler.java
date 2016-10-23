package android.dstyo.com.androidtest.api.handler;

import android.dstyo.com.androidtest.api.core.AsyncRestClient;
import android.dstyo.com.androidtest.api.core.RequestBundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Response handler class that handling core callback request.
 *
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public class GeneralResponseHandler extends JsonHttpResponseHandler {

    private static final String TAG = "GeneralResponseHandler";

    private final AsyncRestClient restClient;
    private final AbstractResponseHandler responseHandler;
    private final RequestBundle requestBundle;
    private final Object tagRequest;

    public GeneralResponseHandler(AsyncRestClient restClient, RequestBundle requestBundle,
                                  Object tagRequest) {
        this.restClient = restClient;
        this.responseHandler = requestBundle.getResponseHandler();
        this.requestBundle = requestBundle;
        this.tagRequest = tagRequest;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        responseHandler.onSuccess(statusCode, headers, response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                          JSONObject errorResponse) {
        onErrorResponse(
                statusCode,
                headers,
                throwable,
                errorResponse
        );
    }

    private void onErrorResponse(int statusCode, Header[] headers, Throwable throwable,
                                 JSONObject errorResponse) {
        if (errorResponse == null) {
            Log.d(TAG, "Throwable: " + throwable.getLocalizedMessage());
            responseHandler.onFailure(statusCode, headers, throwable, new JSONObject());
            return;
        }

        responseHandler.onFailure(statusCode, headers, throwable, new JSONObject());

    }
}