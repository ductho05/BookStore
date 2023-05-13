package com.example.myapplication.activity.productdetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.model.Product;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView btn_pdetail_back;
    ImageView btn_pdetail_search;
    ImageView btn_pdetail_home;
    ImageView btn_pdetail_cart;
    ImageView btn_heart;
    TextView tv_discount_price;
    ImageView btn_add_to_cart;

    private RecyclerView rv_productIntro;
    private RecyclerView rv_readerCare;
    private ProductDetailAdapter productDetailAdapter;
    private ProductDetailAdapter productDetailAdapter2;
    private List<Product> productList1;
    private List<Product> productList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_pdetail_back = (ImageView) findViewById(R.id.btn_pdetail_back);
        btn_pdetail_search = (ImageView) findViewById(R.id.btn_pdetail_search);
        btn_pdetail_home = (ImageView) findViewById(R.id.btn_pdetail_home);
        btn_pdetail_cart = (ImageView) findViewById(R.id.btn_pdetail_cart);
        btn_heart = (ImageView) findViewById(R.id.btn_heart);
        tv_discount_price = (TextView) findViewById(R.id.tv_discount_price);
        btn_add_to_cart = (ImageView) findViewById(R.id.btn_add_to_cart);
        btn_pdetail_back.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_search.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_home.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_heart.setColorFilter(ContextCompat.getColor(this, R.color.red_900));
        btn_add_to_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        tv_discount_price.setPaintFlags(tv_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


//        cài đặt RecyclerView Intro cho trang chi tiết sản phẩm
        rv_productIntro = (RecyclerView) findViewById(R.id.rv_productIntro);

//        Thêm dữ liệu cho product
        productList1 = new ArrayList<>();
//        productList1.add(new Product(1L,"Chúc một ngày tốt lành - Nguyễn Nhật Ánh", "https://upload.wikimedia.org/wikipedia/vi/c/c8/Ch%C3%BAc_m%E1%BB%99t_ng%C3%A0y_t%E1%BB%91t_l%C3%A0nh_cover.jpg",
//                115000, 0.2, "Chúc một ngày tốt lành là tác phẩm của nhà văn chuyên viết truyện cho thiếu nhi Nguyễn Nhật Ánh, kể về cuộc sống hằng ngày của con người cũng như các con vật ở một làng quê. " +
//                "Truyện được chính thức phát hành vào ngày 6 tháng 3 năm 2014 bởi nhà xuất bản Trẻ", "NXB Trẻ", "Nguyễn Nhật Ánh", 2014,
//                288, true));
//
//        productList1.add(new Product(1L,"Chúc một ngày tốt lành - Nguyễn Nhật Ánh", "https://upload.wikimedia.org/wikipedia/vi/c/c8/Ch%C3%BAc_m%E1%BB%99t_ng%C3%A0y_t%E1%BB%91t_l%C3%A0nh_cover.jpg",
//                115000, 0.2, "Chúc một ngày tốt lành là tác phẩm của nhà văn chuyên viết truyện cho thiếu nhi Nguyễn Nhật Ánh, kể về cuộc sống hằng ngày của con người cũng như các con vật ở một làng quê. " +
//                "Truyện được chính thức phát hành vào ngày 6 tháng 3 năm 2014 bởi nhà xuất bản Trẻ", "NXB Trẻ", "Nguyễn Nhật Ánh", 2014,
//                288, true));
//
//        productList1.add(new Product(1L,"Chúc một ngày tốt lành - Nguyễn Nhật Ánh", "https://upload.wikimedia.org/wikipedia/vi/c/c8/Ch%C3%BAc_m%E1%BB%99t_ng%C3%A0y_t%E1%BB%91t_l%C3%A0nh_cover.jpg",
//                115000, 0.2, "Chúc một ngày tốt lành là tác phẩm của nhà văn chuyên viết truyện cho thiếu nhi Nguyễn Nhật Ánh, kể về cuộc sống hằng ngày của con người cũng như các con vật ở một làng quê. " +
//                "Truyện được chính thức phát hành vào ngày 6 tháng 3 năm 2014 bởi nhà xuất bản Trẻ", "NXB Trẻ", "Nguyễn Nhật Ánh", 2014,
//                288, true));
//
//        productList1.add(new Product(1L,"Chúc một ngày tốt lành - Nguyễn Nhật Ánh", "https://upload.wikimedia.org/wikipedia/vi/c/c8/Ch%C3%BAc_m%E1%BB%99t_ng%C3%A0y_t%E1%BB%91t_l%C3%A0nh_cover.jpg",
//                115000, 0.2, "Chúc một ngày tốt lành là tác phẩm của nhà văn chuyên viết truyện cho thiếu nhi Nguyễn Nhật Ánh, kể về cuộc sống hằng ngày của con người cũng như các con vật ở một làng quê. " +
//                "Truyện được chính thức phát hành vào ngày 6 tháng 3 năm 2014 bởi nhà xuất bản Trẻ", "NXB Trẻ", "Nguyễn Nhật Ánh", 2014,
//                288, true));
//
//        productList1.add(new Product(1L,"Chúc một ngày tốt lành - Nguyễn Nhật Ánh", "https://upload.wikimedia.org/wikipedia/vi/c/c8/Ch%C3%BAc_m%E1%BB%99t_ng%C3%A0y_t%E1%BB%91t_l%C3%A0nh_cover.jpg",
//                115000, 0.2, "Chúc một ngày tốt lành là tác phẩm của nhà văn chuyên viết truyện cho thiếu nhi Nguyễn Nhật Ánh, kể về cuộc sống hằng ngày của con người cũng như các con vật ở một làng quê. " +
//                "Truyện được chính thức phát hành vào ngày 6 tháng 3 năm 2014 bởi nhà xuất bản Trẻ", "NXB Trẻ", "Nguyễn Nhật Ánh", 2014,
//                288, true));

        productDetailAdapter = new ProductDetailAdapter(productList1, this);
        rv_productIntro.setAdapter(productDetailAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_productIntro.setLayoutManager(linearLayoutManager);


//        Cài đặt RecycleView ReaderCare cho trang chi tiết sản phẩm
        rv_readerCare = (RecyclerView) findViewById(R.id.rv_readerCare);

//        Thêm dữ liệu cho product

        productList2 = new ArrayList<>();
//        productList2.add(new Product(2L, "Mắt biếc - Nguyễn Nhật Ánh", "https://thuvientinhlaocai.vn/uploads/news/2020_08/gioi-thieu-sach/mat-biec.jpg",
//                110000, 0.1, "Mắt biếc là tiểu thuyết của nhà văn Nguyễn Nhật Ánh trong loạt truyện viết về tình yêu thanh thiếu niên của tác giả này cùng với Thằng quỷ nhỏ, Cô gái đến từ hôm qua,... Đây được xem là một trong những tác phẩm tiêu biểu của Nguyễn Nhật Ánh," +
//                " từng được dịch giả Kato Sakae dịch để giới thiệu với độc giả Nhật Bản với tựa đề Tsuburana hitomi", "NXB Trẻ", "Nguyễn Nhật Ánh", 1990,
//                234, true));
//
//        productList2.add(new Product(2L, "Mắt biếc - Nguyễn Nhật Ánh", "https://thuvientinhlaocai.vn/uploads/news/2020_08/gioi-thieu-sach/mat-biec.jpg",
//                110000, 0.1, "Mắt biếc là tiểu thuyết của nhà văn Nguyễn Nhật Ánh trong loạt truyện viết về tình yêu thanh thiếu niên của tác giả này cùng với Thằng quỷ nhỏ, Cô gái đến từ hôm qua,... Đây được xem là một trong những tác phẩm tiêu biểu của Nguyễn Nhật Ánh," +
//                " từng được dịch giả Kato Sakae dịch để giới thiệu với độc giả Nhật Bản với tựa đề Tsuburana hitomi", "NXB Trẻ", "Nguyễn Nhật Ánh", 1990,
//                234, true));
//
//        productList2.add(new Product(2L, "Mắt biếc - Nguyễn Nhật Ánh", "https://thuvientinhlaocai.vn/uploads/news/2020_08/gioi-thieu-sach/mat-biec.jpg",
//                110000, 0.1, "Mắt biếc là tiểu thuyết của nhà văn Nguyễn Nhật Ánh trong loạt truyện viết về tình yêu thanh thiếu niên của tác giả này cùng với Thằng quỷ nhỏ, Cô gái đến từ hôm qua,... Đây được xem là một trong những tác phẩm tiêu biểu của Nguyễn Nhật Ánh," +
//                " từng được dịch giả Kato Sakae dịch để giới thiệu với độc giả Nhật Bản với tựa đề Tsuburana hitomi", "NXB Trẻ", "Nguyễn Nhật Ánh", 1990,
//                234, true));
//
//        productList2.add(new Product(2L, "Mắt biếc - Nguyễn Nhật Ánh", "https://thuvientinhlaocai.vn/uploads/news/2020_08/gioi-thieu-sach/mat-biec.jpg",
//                110000, 0.1, "Mắt biếc là tiểu thuyết của nhà văn Nguyễn Nhật Ánh trong loạt truyện viết về tình yêu thanh thiếu niên của tác giả này cùng với Thằng quỷ nhỏ, Cô gái đến từ hôm qua,... Đây được xem là một trong những tác phẩm tiêu biểu của Nguyễn Nhật Ánh," +
//                " từng được dịch giả Kato Sakae dịch để giới thiệu với độc giả Nhật Bản với tựa đề Tsuburana hitomi", "NXB Trẻ", "Nguyễn Nhật Ánh", 1990,
//                234, true));
//
//        productList2.add(new Product(2L, "Mắt biếc - Nguyễn Nhật Ánh", "https://thuvientinhlaocai.vn/uploads/news/2020_08/gioi-thieu-sach/mat-biec.jpg",
//                110000, 0.1, "Mắt biếc là tiểu thuyết của nhà văn Nguyễn Nhật Ánh trong loạt truyện viết về tình yêu thanh thiếu niên của tác giả này cùng với Thằng quỷ nhỏ, Cô gái đến từ hôm qua,... Đây được xem là một trong những tác phẩm tiêu biểu của Nguyễn Nhật Ánh," +
//                " từng được dịch giả Kato Sakae dịch để giới thiệu với độc giả Nhật Bản với tựa đề Tsuburana hitomi", "NXB Trẻ", "Nguyễn Nhật Ánh", 1990,
//                234, true));

        productDetailAdapter2 = new ProductDetailAdapter(productList2, this);
        rv_readerCare.setAdapter(productDetailAdapter2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, linearLayoutManager.HORIZONTAL, false);
        rv_readerCare.setLayoutManager(linearLayoutManager2);
    }
}