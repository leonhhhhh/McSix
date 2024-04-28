package com.cachecats.meituan.app;

import android.os.Bundle;

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

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.home_header_tab)
    TabLayout tabLayout;
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;

    private HistoryAdapter historyAdapter;
    private final List<LotteryResp> bottomList = new ArrayList<>();

    private LotteryRepository lotteryRepository;
    private int mTab = 0;
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
        getLotteryHistoryDataFilter();
    }

    public void getLotteryHistoryDataFilter() {
        lotteryRepository.getLotteryHistoryDataFilter(getType(),25,2024,1,2,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LotteryHistoryResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LotteryHistoryResp responseObject) {
                        displayLotteryHistoryData(responseObject);
                    }

                    @Override
                    public void onError(Throwable e) {
//
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void displayLotteryHistoryData(LotteryHistoryResp responseObject) {
        bottomList.clear();
        bottomList.addAll(responseObject.data);
        historyAdapter.notifyDataSetChanged();
    }

    private void init() {
        smartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(this));
        smartRefreshLayout.setRefreshFooter(new CustomRefreshFooter(this, "加载中…"));
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        lotteryRepository = new LotteryRepository();
        //构造Fragment的集合
        historyAdapter = new HistoryAdapter(this,R.layout.history_item,bottomList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinDecoration dividerItemDecoration = new LinDecoration(2);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(historyAdapter);

        historyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ToastUtils.show(bottomList.get(position).issueNumber+"");
            Logger.d("");
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtils.show(tab.getText() != null ? tab.getText().toString() : "");
                mTab = tab.getPosition();
                getLotteryHistoryDataFilter();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private int getType(){
        Logger.d("当前的tab是"+mTab);
        if (mTab == 0){
            return LotteryType.TYPE_MC_6;
        }else if (mTab == 1){
            return LotteryType.TYPE_MC_60;
        }else if (mTab == 2){
            return LotteryType.TYPE_HK_6;
        }else if (mTab == 3){
            return LotteryType.TYPE_HK_60;
        }else {
            return LotteryType.TYPE_MC_6;
        }
    }
}
