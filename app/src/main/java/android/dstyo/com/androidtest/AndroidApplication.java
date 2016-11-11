package android.dstyo.com.androidtest;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * @author Dwi Setiyono <dstyo91@gmail.com>
 * @since 2016.11.12
 */
public class AndroidApplication extends MultiDexApplication{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidApplication.context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
