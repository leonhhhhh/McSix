package com.cachecats.meituan.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class LinDecoration extends RecyclerView.ItemDecoration {
   private int space;

   public LinDecoration(int space) {
      this.space = space;
   }

   @Override
   public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
      super.getItemOffsets(outRect, view, parent, state);
      outRect.set(0,0,0,space);

   }
}
