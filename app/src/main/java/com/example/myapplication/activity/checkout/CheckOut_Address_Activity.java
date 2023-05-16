package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.api.MapApiService;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.NominatimResult;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOut_Address_Activity extends AppCompatActivity {

    private EditText ed_name;
    private EditText ed_phone;
    private EditText ed_city;
    private EditText ed_address;
    private EditText ed_message;
    private Button btnNextOrder;

    private ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.checkout_address_activity);
        AnhXa();
        btnNextOrder.setEnabled(false);
        btnNextOrder.setBackgroundColor(Color.rgb(245,246,250));
        validate(ed_name);
        validate(ed_phone);
        validate(ed_city);
        validate(ed_address);

        btn_back.setOnClickListener(view -> {
            noticeCancelCheckout();
        });

        btnNextOrder.setOnClickListener(view -> {
            Order order = new Order();
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            ArrayList<CartItemModel> cartItems = new ArrayList<>();
            cartItems = getData(order, orderItems);
            supportFinishAfterTransition();
            Intent intent = new Intent(CheckOut_Address_Activity.this, Payment_Method.class);
            for (CartItemModel i:cartItems) {
                Log.e("ListCartItem Cart: ", i.getProduct().getTitle());
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("cartItems", cartItems);
            intent.putExtras(bundle);
            intent.putExtra("order", order);
            intent.putExtra("orderItems", orderItems);

            String address = order.getAddress() + " " + order.getCity();
            Log.e("Địa chỉ: ",address);
            MapApiService.apiService.getCoordinates(address, "json", 1).enqueue(new Callback<List<NominatimResult>>() {
                @Override
                public void onResponse(Call<List<NominatimResult>> call, Response<List<NominatimResult>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                      NominatimResult  result = response.body().get(0);
                      float addressShipping_Lat = Float.parseFloat(result.getLat());
                      float addressShipping_Lon = Float.parseFloat(result.getLon());
                      intent.putExtra("lat", addressShipping_Lat);
                      intent.putExtra("lon", addressShipping_Lon);
                      startActivity(intent);
                      overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
                      Toast.makeText(getApplicationContext(), "Tìm thấy địa chỉ giao hàng",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy địa chỉ giao hàng",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<NominatimResult>> call, Throwable t) {
                    Log.e("Lấy địa chỉ lỗi", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Loi " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
    }

    @Override
    public void onBackPressed() {
        noticeCancelCheckout();
    }

    private void noticeCancelCheckout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_cancel, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button buttonOK = viewDialog.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(view -> {
            Intent intent1 = new Intent(CheckOut_Address_Activity.this, HomeActivity.class);
            startActivity(intent1);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
    }

    private void validate(EditText edText) {
        edText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if (text.isEmpty()) {
                    edText.setError("Vui lòng nhập trường này");
                    btnNextOrder.setEnabled(false);
                    btnNextOrder.setBackgroundColor(Color.rgb(245,246,250));
                } else {
                    btnNextOrder.setEnabled(true);
                    btnNextOrder.setBackgroundColor(Color.rgb(77,177,136));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.isEmpty()) {
                    edText.setError("Vui lòng nhập trường này");
                }
            }
        });
    }
    private ArrayList<CartItemModel> getData(Order order, ArrayList<OrderItem> orderItems) {
        String name = String.valueOf(ed_name.getText());
        String phone = String.valueOf(ed_phone.getText());
        String city = String.valueOf(ed_city.getText());
        String address = String.valueOf(ed_address.getText());
        String message = String.valueOf(ed_message.getText());
        order.setName(name);
        order.setPhone(phone);
        order.setCity(city);
        order.setAddress(address);
        order.setMessage(message);
//        Set cứng user
        User user = new User("64477d8318a87d6e84a366d0", false, "ductho", "123", "Ninh Duc Tho2",
                "https://res.cloudinary.com/dgntuytuu/image/upload/v1682407093/book-store/uuphcpbb3qadnh8eyoui.jpg",
                "","tho@gmail.com", "","","", null, "2023-04-25T07:13:07.766+00:00",
                "2023-04-25T07:18:46.440+00:00");
        order.setUser(user.get_id());

//        Lấy danh sách sản phẩm thanh toán
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        ArrayList<CartItemModel> cartItems =  bundle.getParcelableArrayList("listCartItem");
//          Lấy danh sách sản phẩm và tổng số lượng
        int quantity = 0;
        for (CartItemModel item : cartItems) {
            quantity += item.getQuantity();
            orderItems.add(new OrderItem(item.getQuantity(), (float) item.getProduct().getPrice() * item.getQuantity(), "", item.getProduct().get_id()));
        }
        float total_price = (float) intent.getDoubleExtra("total_price", 0.0f);
        order.setPrice(total_price);
        order.setQuantity(quantity);

        for (CartItemModel item : cartItems) {
            Log.e("orderItem Payment", item.getProduct().title);
        }

        return cartItems;
    }

    private void AnhXa() {
        ed_name = findViewById(R.id.name);
        ed_phone = findViewById(R.id.phone);
        ed_city = findViewById(R.id.city);
        ed_address = findViewById(R.id.address);
        ed_message = findViewById(R.id.message);
        btnNextOrder = findViewById(R.id.btn_nextOrder);
        btn_back = findViewById(R.id.btn_back);
    }
}