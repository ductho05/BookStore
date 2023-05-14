//package com.example.myapplication.helper;
//
//import android.widget.TextView;
//
//import com.example.myapplication.model.CartItemModel;
//import com.example.myapplication.model.CartModel;
//
//import java.util.ArrayList;
//
//public class ManagementCart {
//    public String priceControl(ArrayList<CartItemModel> arrayList) {
//        double totalPrice = 0;
//        for (int i = 0; i < arrayList.size(); i++) {
////            totalCount = totalCount + arrayList.get(i).getAmount();
//            double itemPrice = Double.parseDouble(arrayList.get(i).getSalePriceItem()) * arrayList.get(i).getAmount();
//            totalPrice += itemPrice;
//        }
//        return String.valueOf(totalPrice);
//    }
//    public void updatePrice(ArrayList<CartModel> listDomains, TextView textView) {
//        textView.setText(priceControl(listDomains));
//    }
//}