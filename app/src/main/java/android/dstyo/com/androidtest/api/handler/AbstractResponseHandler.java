package android.dstyo.com.androidtest.api.handler;


import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

/**
 * Representing abstract response handler to Generic Object.
 *
 * @param <T> Generic type that used on return type
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.10.22
 */
public abstract class AbstractResponseHandler<T> extends JsonHttpResponseHandler {

    private static final String LOG_TAG = "ANDROID_TEST";

    /**
     * Creates new JsonHttpResponseHandler, with JSON String encoding UTF-8.
     */
    public AbstractResponseHandler() {
        super(DEFAULT_CHARSET);
    }

    /**
     * Call onSuccess method from response body and generated list object
     * for custom implementation from response body purpose.
     *
     * @param responseBody json response body
     * @param object       generated object
     */
    protected void onSuccess(JSONObject responseBody, T object) {
        onSuccess(object);
    }

    /**
     * Call onSuccess method from response body and generated list object
     * for custom implementation from response body purpose.
     *
     * @param responseBody json response body
     * @param object       generated object
     */
    protected void onSuccess(JSONArray responseBody, T object) {
        onSuccess(object);
    }


    /**
     * Called when desired Object has been converted from callback.
     *
     * @param object the desired generic object
     */
    public void onSuccess(T object) {
        Log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    /**
     * Called when desired List Object has been converted from callback.
     * Implemented when generic param is part of {@link java.util.Collections}
     *
     * @param objectList the list object from response
     * @param totalItem  the total item of list object
     * @param page       the page number of received list object
     */
    protected void onSuccess(T objectList, int totalItem, int page) {
        onSuccess(objectList);
    }

    /**
     * Called when desired List Object has been converted from callback.
     * Implemented when generic param is part of {@link java.util.Collections}
     *
     * @param objectList the list object from response
     * @param totalItem  the total item of list object
     */
    protected void onSuccess(T objectList, int totalItem) {
        onSuccess(objectList);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        if (statusCode == HttpStatus.SC_REQUEST_TIMEOUT) {
            onRequestTimedOut();
        }
        else {
            onFailure(errorResponse);
        }
    }

    /**
     * Called when onFailure(int, Header[], Throwable, JSONObject) was called
     * without <tt>408 Request Timeout</tt> statusCode.
     *
     * @param errorResponse parsed response if any
     */
    public void onFailure(JSONObject errorResponse) {
        onFailure();
    }

    /**
     * Used to catch the throw statement if parameter need try/catch surrounding.
     */
    public void onFailure() {
        Log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received");
    }

    /**
     * Called when request timed out.
     */
    public void onRequestTimedOut() {
        Log.w(LOG_TAG, "onRequestTimedOut() was not overriden, but callback was received");
    }

    @Override
    public void onSuccess(final int statusCode, final Header[] headers, final JSONArray response) {
        if (response == null) {
            onSuccess(statusCode, headers, new JSONArray());
            return;
        }

        Runnable parser = new Runnable() {
            @Override
            public void run() {
                try {

                    final T object = parseResponse(response);
                    postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess(response, object);
                        }
                    });

                }
                catch (final Exception ex) {
                    postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            onFailure(statusCode, headers, ex, (JSONObject) null);
                        }
                    });
                }
            }
        };

        if (!getUseSynchronousMode()) {
            new Thread(parser).start();
        }
        else {
            // In synchronous mode everything should be run on one thread
            parser.run();
        }
    }

    /**
     * Return Generic Object that instantiated from body response.
     *
     * @param responseBody JSON responseBody from API
     * @return T Object
     */
    protected abstract T parseResponse(JSONArray responseBody);

}
