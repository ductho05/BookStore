package com.example.myapplication.constants;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppConstants {
    public static final String SHOP_ADDRESS = "228 đường số 6, Linh Chiểu, Thủ Đức, Hồ Chí Minh";
    public static final String API_MAPS = "https://nominatim.openstreetmap.org/";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public static void validateEmail(EditText email, TextView err_text, Button btn) {

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String emailValue = charSequence.toString();
                Matcher matcher = EMAIL_PATTERN.matcher(emailValue);
                if (!matcher.matches()) {
                    err_text.setText("Vui lòng nhập đúng định dạng email");
                    btn.setEnabled(false);
                    btn.setBackgroundColor(Color.rgb(245,246,250));
                } else {
                    err_text.setText("");
                    btn.setEnabled(true);
                    btn.setBackgroundColor(Color.rgb(77,177,136));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
