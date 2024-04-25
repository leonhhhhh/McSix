package com.cachecats.meituan.app;

import android.os.Bundle;

import com.cachecats.meituan.MyApplication;
import com.cachecats.meituan.R;
import com.cachecats.meituan.base.BaseActivity;
import com.cachecats.meituan.di.components.DaggerActivityComponent;
import com.cachecats.meituan.di.modules.ActivityModule;
import com.cachecats.meituan.widget.bottomtab.CustomBottomTabWidget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends BaseActivity {

//    @BindView(R.id.tabWidget)
//    CustomBottomTabWidget tabWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);

        //初始化
        init();
    }

    private void init() {
        //构造Fragment的集合

    }
}
