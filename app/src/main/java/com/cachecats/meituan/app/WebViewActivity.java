package com.cachecats.meituan.app;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cachecats.meituan.MyApplication;
import com.cachecats.meituan.R;
import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.api.repository.LotteryRepository;
import com.cachecats.meituan.app.history.HistoryAdapter;
import com.cachecats.meituan.base.BaseActivity;
import com.cachecats.meituan.constants.LotteryType;
import com.cachecats.meituan.di.components.DaggerActivityComponent;
import com.cachecats.meituan.di.modules.ActivityModule;
import com.cachecats.meituan.utils.ToastUtils;
import com.cachecats.meituan.widget.LinDecoration;
import com.cachecats.meituan.widget.refresh.CustomRefreshFooter;
import com.cachecats.meituan.widget.refresh.CustomRefreshHeader;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
        //初始化
        init();
    }

    private void init() {
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        webView.clearCache(true);
        super.onDestroy();
    }
}
