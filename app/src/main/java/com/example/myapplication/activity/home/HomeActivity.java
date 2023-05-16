package com.example.myapplication.activity.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.login.ProfileActivity;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.adapter.SlideAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.Slide;
import com.example.myapplication.model.resObj;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String QUALITY = "12";
    //private String RANDOM_CATE = "";
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private SlideAdapter photoAdapter;

    SwipeRefreshLayout homeSwipeRefreshLayout;
    private List<Slide> mListPhoto;

    private Timer mTimer;

    private TextView btnsearch, tvNewSeeMore, tvRunSeeMore;


    private TextView txtTimer;

    CountDownTimer Timer;

    private RecyclerView.Adapter adapter;

//    private ProductDetailAdapter productDetailAdapter;
    private  RecyclerView listViewLastBook, listViewForYou, listViewBestSeller, listViewFlashSale;
    List<Product> products;


    LinearLayout btnUser, imgCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        AnhXa();

        ClickOneThing();
        //setViewFlashSale();
        onStart();
        setSlideHome();
        setViewLastBook();
        setViewForYou();
        setViewBestSeller();
    }

    public String getQuality() {
        return QUALITY;
    }


    @SuppressLint("ClickableViewAccessibility")
    public void ClickOneThing() {
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        tvNewSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Truyền du lieu qua CategoryActivity
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra("Filter", "Mới nhất");
                startActivity(intent);
            }
        });

        tvRunSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Truyền du lieu qua CategoryActivity
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra("Filter", "Bán chạy nhất");
                startActivity(intent);
            }
        });

        btnsearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.drawable.my_view_pressed);
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.white_backgroud);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }




    @Override
    public void onStart() {
        super.onStart();
        long now = System.currentTimeMillis();
        long midnight = getMidnight(now);
        onRefresh();


        CountDownTimer timer = new CountDownTimer(midnight - now, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int hours = (int) (millisUntilFinished / (60 * 60 * 1000));
                int minutes = (int) ((millisUntilFinished / (60 * 1000)) % 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                txtTimer.setText(time);
            }

            @Override
            public void onFinish() {
                onStart();
            }
        };
        timer.start();
    }

    private String getRandomCateFlashSale() {

        int[] cates = {871,
                316,
                664,
                886,
                8322,
                1856,
                861,
                6750,
                665,
                7671,
                1367,
                1922,
                593,
                9733,
                843,
                9721,
                1754,
                385,
                5246};
        Random rand = new Random();
        int randomIndex = rand.nextInt(cates.length);
        return String.valueOf(cates[randomIndex]);
    }

    private int getCurrentHour() {
        int hour = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hour = LocalTime.now().getHour();
        }
        return hour;
    }

    private long getMidnight(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, (getCurrentHour()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calendar.set(Calendar.MINUTE, LocalTime.now().getMinute()+1);
        }


        calendar.set(Calendar.SECOND, 0 );

        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    public void AnhXa() {
        imgCart = findViewById(R.id.imgCart);
        btnUser = findViewById(R.id.btnUser);
        tvNewSeeMore = findViewById(R.id.tvNewSeeMore);
        tvRunSeeMore = findViewById(R.id.tvRunSeeMore);
        btnsearch = findViewById(R.id.editTextTextPersonName);
        txtTimer = findViewById(R.id.txtGio);
        listViewLastBook = findViewById(R.id.view);
        listViewFlashSale = findViewById(R.id.viewflash);
        listViewForYou = findViewById(R.id.viewforyou);
        listViewBestSeller = findViewById(R.id.viewbestseller);
        viewPager = findViewById(R.id.viewpaper);
        circleIndicator = findViewById(R.id.circle_indicator);
    }

    private void setSlideHome() {
        mListPhoto = getListPhoto();
        photoAdapter = new SlideAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImages();
    }

    private void setViewFlashSale() {
        ApiService.apiService.getFilterProduct(getRandomCateFlashSale(), "discount", 1, 2000, "desc", Integer.parseInt(QUALITY), null).enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    products = response.body().getData();
                    adapter = new ProductDetailAdapter(products, HomeActivity.this);
                    listViewFlashSale.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    listViewFlashSale.setLayoutManager(linearLayoutManager);
                    listViewFlashSale.setAdapter(adapter);
                    //productDetailAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private List<Slide> getListPhoto() {
        List<Slide> list= new ArrayList<>();
        list.add(new Slide(R.drawable.slide_home_1));
        list.add(new Slide(R.drawable.slide_home_2));
        list.add(new Slide(R.drawable.slide_home_3));

        return list;

    }
    private void setViewLastBook() {
                ApiService.apiService.getFilterProduct("", "published_date", 1, 20, "desc", Integer.parseInt(QUALITY), null).enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {

                if (response.isSuccessful()) {
                    products = response.body().getData();
                    adapter = new ProductDetailAdapter(products, HomeActivity.this);
                    listViewLastBook.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    listViewLastBook.setLayoutManager(linearLayoutManager);
                    listViewLastBook.setAdapter(adapter);
                    //productDetailAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void setViewBestSeller() {
        ApiService.apiService.getFilterProduct("", "sold", 1, 20, "desc", Integer.parseInt(QUALITY), null).enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    products = response.body().getData();
                    adapter = new ProductDetailAdapter(products, HomeActivity.this);
                    listViewBestSeller.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    listViewBestSeller.setLayoutManager(linearLayoutManager);
                    listViewBestSeller.setAdapter(adapter);
                    //productDetailAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }


    public void setViewForYou() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        listViewForYou.setLayoutManager(layoutManager);
        ArrayList<Product> news = new ArrayList<>();

        adapter = new ProductDetailAdapter(news, this);
        listViewForYou.setAdapter(adapter);
    }

    private void autoSlideImages() {
        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager == null) {
            return;
        }
        // Init Timer
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem += 1;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }

                    }
                });

            }
        }, 500, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    @Override
    public void onRefresh() {

        setViewFlashSale();
//        setViewLastBook();
//        setViewBestSeller();
//        setViewForYou();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                homeSwipeRefreshLayout.setRefreshing(false);
//            }
//        }, 2000);
    }
}