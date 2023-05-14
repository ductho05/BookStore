package com.example.myapplication.helper;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScroll extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    public PaginationScroll(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

        if(dy > 0){
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItem = layoutManager.findFirstVisibleItemPosition();

            if (isLoading() || isLastPage()){
                return;
            }

            if((visibleItemCount + pastVisibleItem) >= totalItemCount && pastVisibleItem >= 0){
                    loadMoreItems();
                
            }
        }
    }

    public abstract void loadMoreItems();
    public abstract boolean isLoading();
    public abstract boolean isLastPage();


}
