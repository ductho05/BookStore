package com.example.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.constants.AppConstants;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private EditText editText_emailRegister, editText_userNameRegister, editText_passwordRegister;
    private TextView textView_errEmail, textView_errPw, textView_errUsername;
    private Button btn_signup;
    private View view;
    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_register, container, false);
        AnhXa();
        Validate(editText_emailRegister, textView_errEmail);
        Validate(editText_passwordRegister, textView_errPw);
        Validate(editText_userNameRegister, textView_errUsername);
        AppConstants.validateEmail(editText_emailRegister, textView_errEmail, btn_signup);
        register();
        return view;
    }

    private void register() {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_emailRegister.getText().toString();
                String password = editText_passwordRegister.getText().toString();
                String userName = editText_userNameRegister.getText().toString();
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(userName);

                ApiService.apiService.getUserByEmail(email).enqueue(new Callback<resObj<User>>() {
                    @Override
                    public void onResponse(Call<resObj<User>> call, Response<resObj<User>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            noticeEmailValid();
                        } else {
                            ApiService.apiService.registerAccount(user).enqueue(new Callback<resObj<User>>() {
                                @Override
                                public void onResponse(Call<resObj<User>> call, Response<resObj<User>> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        noticeSuccessRegoster();
                                    } else {
                                        noticeFailedRegoster();
                                    }
                                }

                                @Override
                                public void onFailure(Call<resObj<User>> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<resObj<User>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void noticeSuccessRegoster() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_login_success, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView message_err = viewDialog.findViewById(R.id.notice_success);
        message_err.setText("Đăng ký thành công");
    }

    private void noticeFailedRegoster() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_login_failed, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView message_err = viewDialog.findViewById(R.id.message_err);
        message_err.setText("Vui lòng kiểm tra lại thông tin đăng kí");
    }

    private void noticeEmailValid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_login_failed, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView message_err = viewDialog.findViewById(R.id.message_err);
        message_err.setText("Email đã có tài khoản");
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
                    btn_signup.setEnabled(false);
                    btn_signup.setBackgroundColor(Color.rgb(245,246,250));
                } else {
                    errText.setText("");
                    btn_signup.setEnabled(true);
                    btn_signup.setBackgroundColor(Color.rgb(77,177,136));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void AnhXa() {
        editText_emailRegister = view.findViewById(R.id.editText_emailRegister);
        editText_userNameRegister = view.findViewById(R.id.editText_userNameRegister);
        editText_passwordRegister = view.findViewById(R.id.editText_passwordRegister);
        textView_errEmail = view.findViewById(R.id.textView_errEmail);
        textView_errPw = view.findViewById(R.id.textView_errPw);
        textView_errUsername = view.findViewById(R.id.textView_errUsername);
        btn_signup = view.findViewById(R.id.btn_signup);
    }
}