package android.dstyo.com.androidtest.page.users;


import android.dstyo.com.androidtest.base.AbstractAsyncRequestDialogFragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.dstyo.com.androidtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAddFragment extends AbstractAsyncRequestDialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_add, container, false);
    }

    @Override
    public Object getTagRequest() {
        return null;
    }
}
