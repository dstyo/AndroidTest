package android.dstyo.com.androidtest;

import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;


/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.14
 */
public class UserUnitTest extends InstrumentationTestCase {

    public void testGetUserAsyncHttpClient() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        final AsyncHttpClient httpClient = new AsyncHttpClient();
        final StringBuilder strBuilder = new StringBuilder();

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                httpClient
                        .get(
                                "http://rental-android-test.herokuapp.com/users.json",
                                new JsonHttpResponseHandler() {
                                    public void onFinish() {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                                        //strBuilder.append(responseBody);
                                        Log.d("hasil",responseBody.toString());
                                        signal.countDown();
                                    }

                                });
            }
        });

        try {
            signal.await(30, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject jsonRes = new JSONObject(strBuilder.toString());
        try {
            // Test your jsonResult here
            assertEquals(1, jsonRes.getInt("id"));
        } catch (Exception e) {

        }

        assertEquals(0, signal.getCount());
    }
}
