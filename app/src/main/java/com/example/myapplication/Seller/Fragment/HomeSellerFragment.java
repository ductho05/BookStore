package com.example.myapplication.seller.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.seller.activity.GraphViewCategoryActicity;
import com.example.myapplication.seller.activity.GraphViewOrderActicity;
import com.example.myapplication.seller.activity.GraphViewProductActicity;
import com.example.myapplication.seller.activity.GraphViewUserActicity;

import java.util.ArrayList;
import java.util.List;


public class HomeSellerFragment extends Fragment {

    View view;
    ImageView imgAdmin1, imgAdmin2, imgAdmin3, imgAdmin4;
    LinearLayout tvProduct, tvOrder, tvAccount, tvCategory, tvBrand, tvReport, tvSetting, tvLogout, admin1, admin2, admin3, admin4;
    public HomeSellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_seller, container, false);
        AnhXa();
        List<String> list = new ArrayList<>();
        list.add("https://www.facebook.com/ducanh1811");
        list.add("https://www.facebook.com/ducthoute");
        list.add("https://www.facebook.com/profile.php?id=100004233285990");
        list.add("https://www.facebook.com/profile.php?id=100012092682416");

        List <String> listAdminimage = new ArrayList<>();
        listAdminimage.add("https://scontent.fsgn5-6.fna.fbcdn.net/v/t39.30808-6/315314168_1482626432246324_6846010179688508405_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=174925&_nc_ohc=TYUiDtn0_PIAX_A1cH6&_nc_ht=scontent.fsgn5-6.fna&oh=00_AfCHbC3ecC39ITHAdhp_VmIOHSVnKCWtFEYL9y128xiNIw&oe=6468DEBF");
        listAdminimage.add("https://scontent.fsgn5-9.fna.fbcdn.net/v/t1.6435-9/147800952_254324376223592_8020154082087879309_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=MHEursqt5U8AX8AiTCv&_nc_ht=scontent.fsgn5-9.fna&oh=00_AfBUMhIu1v1jySnFl-c9UxZgrT2zSf4UPjBXYHCk8x6rHw&oe=648C5141");
        listAdminimage.add("https://scontent.fsgn5-10.fna.fbcdn.net/v/t1.6435-9/52016148_1179126998905065_8866861318603276288_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=0VIFnz2fQ14AX8Bm0Eg&_nc_oc=AQnweJID4znTgtIivf5knufwoHHw35I3XdSIYdVI9yduXDpF8UI0bH8QIiWoPA8ZtHQ&_nc_ht=scontent.fsgn5-10.fna&oh=00_AfAJcuqe3BYiM-v0UL7d2BziLNMvt_bjLnlpbl_R_KOmtA&oe=648C4481");
        listAdminimage.add("https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.6435-9/183685638_1151600685253010_1480996373795678731_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=dzM1M5kB_JMAX_Zkt3p&_nc_ht=scontent.fsgn5-6.fna&oh=00_AfCIFcLigbcQIfFJJg3e2yXIv-iSsD0FtD8L7siG_Rkk9A&oe=648C5294");
        SetImageAdmin(listAdminimage);
        ClickOneThing(list);
        return view;
    }

    private void SetImageAdmin(List <String> listAdminimage) {
        Glide.with(getActivity()).load(listAdminimage.get(0)).into(imgAdmin1);
        Glide.with(getActivity()).load(listAdminimage.get(1)).into(imgAdmin2);
        Glide.with(getActivity()).load(listAdminimage.get(2)).into(imgAdmin3);
        Glide.with(getActivity()).load(listAdminimage.get(3)).into(imgAdmin4);
    }
    private void ClickOneThing(List<String> listAdmin) {
        tvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewProductActicity.class);
                startActivity(intent);
            }
        });
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewOrderActicity.class);
                startActivity(intent);
            }
        });

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewUserActicity.class);
                startActivity(intent);
            }
        });

        tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewCategoryActicity.class);
                startActivity(intent);
            }
                }   );

        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(0)));
                startActivity(intent);
            }
        });

        admin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(1);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(1)));
                startActivity(intent);
            }
        });

        admin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(2);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(2)));
                startActivity(intent);
            }
        });

        admin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(3);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(3)));
                startActivity(intent);
            }
        });


    }

    private void AnhXa() {
        tvProduct = view.findViewById(R.id.tvptSanpham);
        tvAccount = view.findViewById(R.id.tvptNguoidung);
        tvOrder = view.findViewById(R.id.tvptDonhang);
        tvCategory = view.findViewById(R.id.tvptDanhmuc);
        admin2 = view.findViewById(R.id.admin2);
        admin3 = view.findViewById(R.id.admin3);
        admin4 = view.findViewById(R.id.admin4);
        admin1 = view.findViewById(R.id.admin1);
        imgAdmin1 = view.findViewById(R.id.imgAdmin1);
        imgAdmin2 = view.findViewById(R.id.imgAdmin2);
        imgAdmin3 = view.findViewById(R.id.imgAdmin3);
        imgAdmin4 = view.findViewById(R.id.imgAdmin4);
    }
}