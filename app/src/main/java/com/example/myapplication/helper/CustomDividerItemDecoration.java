package com.example.myapplication.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDividerItemDecoration extends DividerItemDecoration {
    private Paint mPaint;

    public CustomDividerItemDecoration(Context context, int resId) {
        super(context, VERTICAL);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        mPaint = new Paint();
        mPaint.setShader(shader);
        mPaint.setStrokeWidth(bitmap.getHeight());
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getChildCount();
        int lastItemIndex = itemCount - 1;

        for (int i = 0; i < lastItemIndex; i++) {
            View item = parent.getChildAt(i);

            int top = item.getBottom();
            int bottom = (int) (top + mPaint.getStrokeWidth());

            c.drawLine(item.getLeft(), top, item.getRight(), bottom, mPaint);
        }
    }
}
