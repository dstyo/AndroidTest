package android.dstyo.com.androidtest.page.users;


import android.app.Activity;
import android.dstyo.com.androidtest.R;
import android.dstyo.com.androidtest.api.handler.BooleanResponseHandler;
import android.dstyo.com.androidtest.api.handler.UserResponseHandler;
import android.dstyo.com.androidtest.api.request.UserRequest;
import android.dstyo.com.androidtest.base.AbstractAsyncRequestDialogFragment;
import android.dstyo.com.androidtest.constant.RequestConstant;
import android.dstyo.com.androidtest.model.User;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAddFragment extends AbstractAsyncRequestDialogFragment {

    private TextInputLayout textInputName;
    private TextInputLayout textInputMobilePhone;
    private TextInputLayout textInputAddress;
    private Button btnSave;
    private int userId;
    private ImageView ivCrossBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add, container, false);
        textInputName = (TextInputLayout) view.findViewById(R.id.text_input_name);
        textInputMobilePhone = (TextInputLayout) view.findViewById(R.id.text_input_mobile_phone);
        textInputAddress = (TextInputLayout) view.findViewById(R.id.text_input_address);
        ivCrossBtn = (ImageView) view.findViewById(R.id.iv_cross);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    setParam();
                }
            }
        });

        ivCrossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if (null != bundle) {
            userId = bundle.getInt(RequestConstant.USER_ID);
            btnSave.setVisibility(View.GONE);
            getDetailUser();
        }
        return view;
    }

    private void getDetailUser() {
        if (!isInternetPresent()) {
            setNoInternetConnection(getView());
            return;
        }

        showProgressLoading("Get Detail User ...");
        (new UserRequest(this)).getDetailUsers(
                userId,
                new UserResponseHandler() {
                    @Override
                    public void onSuccess(User user) {
                        hideProgressLoading();
                        setDetailUser(user);
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {

                    }

                    @Override
                    public void onRequestTimedOut() {
                        Toast.makeText(getContext(), R.string.msg_internet_timed_out, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    private void setDetailUser(User user) {
        textInputName.getEditText().setEnabled(false);
        textInputAddress.getEditText().setEnabled(false);
        textInputMobilePhone.getEditText().setEnabled(false);
        textInputName.getEditText().setText(user.getName());
        textInputAddress.getEditText().setText(user.getAddress());
        textInputMobilePhone.getEditText().setText(user.getMobilePhone());
    }

    private void setParam() {
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("name", textInputName.getEditText().getText());
            jsonParams.put("address", textInputMobilePhone.getEditText().getText());
            jsonParams.put("mobile", textInputAddress.getEditText().getText());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        addUser(jsonParams);
    }

    private boolean validateForm() {
        resetError();
        View requestFocusView = null;

        if (TextUtils.isEmpty(textInputName.getEditText().getText())) {
            textInputName.setError("Nama tidak boleh kosong");
            requestFocusView = setRequestView(requestFocusView, textInputName);
        }
        if (TextUtils.isEmpty(textInputMobilePhone.getEditText().getText())) {
            textInputName.setError("Nomor Handphone tidak boleh kosong");
            requestFocusView = setRequestView(requestFocusView, textInputMobilePhone);
        }
        if (TextUtils.isEmpty(textInputAddress.getEditText().getText())) {
            textInputName.setError("Alamat tidak boleh kosong");
            requestFocusView = setRequestView(requestFocusView, textInputName);
        }

        if (null != requestFocusView) {
            requestFocusView.requestFocus();
            return false;
        }

        return true;
    }

    private void resetError() {
        textInputName.setError(null);
        textInputMobilePhone.setError(null);
        textInputAddress.setError(null);
    }

    private void addUser(JSONObject jsonParams) {
        if (!isInternetPresent()) {
            setNoInternetConnection(getView());
            return;
        }

        showProgressLoading("Adding users...");

        (new UserRequest(this)).addUsers(
                jsonParams,
                new BooleanResponseHandler() {
                    @Override
                    public void onSuccess(Boolean status) {
                        userAddNotify();
                    }

                    @Override
                    public void onFailure(JSONObject errorResponse) {

                    }

                    @Override
                    public void onRequestTimedOut() {
                        Toast.makeText(getContext(), R.string.msg_internet_timed_out, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void userAddNotify() {
        hideProgressLoading();
        dismissAndNotifyTarget(
                RequestConstant.ADD_USER,
                Activity.RESULT_OK
        );
    }

    private View setRequestView(View requestViewHolder, View requestView) {
        return null == requestViewHolder ? requestView : requestViewHolder;
    }

    @Override
    public Object getTagRequest() {
        return null;
    }
}
