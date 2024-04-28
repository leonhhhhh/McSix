package com.cachecats.meituan.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

class BoldTextview extends AppCompatTextView {
    public BoldTextview(Context context) {
        super(context);
        setBold();
    }

    public BoldTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBold();
    }

    public BoldTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBold();
    }
    private void setBold() {
        setTypeface(null, Typeface.BOLD);
    }

}
