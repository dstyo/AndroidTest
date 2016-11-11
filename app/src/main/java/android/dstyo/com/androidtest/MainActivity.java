package android.dstyo.com.androidtest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.dstyo.com.androidtest.api.interfaces.TagInterface;
import android.dstyo.com.androidtest.page.main.HomeFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TagInterface {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, homeFragment)
                .commit();
    }

    @Override
    public Object getTagRequest() {
        return TAG;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
