package com.cachecats.meituan.app.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cachecats.meituan.R;
import com.cachecats.meituan.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by solo on 2018/1/8.
 */

public class OrderFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);
        return view;

    }
}
