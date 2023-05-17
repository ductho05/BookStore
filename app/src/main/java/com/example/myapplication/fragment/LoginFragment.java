package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.account.LoginManager;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.constants.AppConstants;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private TextView err_email, err_password;
    private EditText email, password;
    private Button btn_login;
    private View mView;
    Context mContext;
    SharedPreferences sharedPreferences;
    LoginManager loginManager;
    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_login, container, false);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginManager = new LoginManager(sharedPreferences);
        AnhXa();
        btn_login.setEnabled(false);
        btn_login.setBackgroundColor(Color.rgb(245,246,250));
        Validate(email, err_email);
        Validate(password, err_password);
        AppConstants.validateEmail(email, err_email, btn_login);
        Login();
        return mView;
    }

    private void Login() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                ApiService.apiService.getUserByEmail(emailValue).enqueue(new Callback<resObj<User>>() {
                    @Override
                    public void onResponse(Call<resObj<User>> call, Response<resObj<User>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            User user = response.body().getData();
                            String emailLogin = user.getEmail();
                            String passwordLogin = user.getPassword();
                            String id = user.get_id();
                            if (emailValue.equals(emailLogin) && passwordValue.equals(passwordLogin)) {
                                loginManager.saveLogin(id, true);
                                Intent intent = new Intent(getContext(), HomeActivity.class);
                                intent.putExtra("isLogin", true);
                                startActivity(intent);
                            } else {
                                noticeFailedLogin();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<resObj<User>> call, Throwable t) {
                        Log.e("Logggggim: ", t.getMessage());
                    }
                });
            }
        });
    }

    private void noticeFailedLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_login_failed, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void Validate(EditText editText, TextView errText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if (text.isEmpty()) {
                    errText.setText("Vui lòng nhập trường này");
                    btn_login.setEnabled(false);
                    btn_login.setBackgroundColor(Color.rgb(245,246,250));
                } else {
                    errText.setText("");
                    btn_login.setEnabled(true);
                    btn_login.setBackgroundColor(Color.rgb(77,177,136));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void AnhXa() {
        err_email = mView.findViewById(R.id.textView_errEmail);
        err_password = mView.findViewById(R.id.textView_errPw);
        email = mView.findViewById(R.id.editText_emailLogin);
        password = mView.findViewById(R.id.editText_passwordLogin);
        btn_login = mView.findViewById(R.id.btnSignin);
    }

}