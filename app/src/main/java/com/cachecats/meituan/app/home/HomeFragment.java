package com.cachecats.meituan.app.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cachecats.domin.shop.model.ShopModel;
import com.cachecats.meituan.MyApplication;
import com.cachecats.meituan.R;
import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryPic;
import com.cachecats.meituan.api.model.LotteryPicListResp;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.api.model.WinningNumberInfo;
import com.cachecats.meituan.app.HistoryActivity;
import com.cachecats.meituan.app.home.adapter.ShopListAdapter;
import com.cachecats.meituan.base.BaseFragment;
import com.cachecats.meituan.di.components.DaggerActivityComponent;
import com.cachecats.meituan.utils.ToastUtils;
import com.cachecats.meituan.widget.SpaceItemDecoration;
import com.cachecats.meituan.widget.refresh.CustomRefreshFooter;
import com.cachecats.meituan.widget.refresh.CustomRefreshHeader;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by solo on 2018/1/8.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {
    //小模块GridView布局
    @BindView(R.id.recyclerview_little_module)
    RecyclerView recyclerView;
    //下拉刷新组件
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.home_header_tab)
    TabLayout tabLayout;
    @BindView(R.id.home_header_current_history)
    TextView tvHistory;
    @BindView(R.id.llyLasted)
    LinearLayout llyLasted;
    @BindView(R.id.llyHistory)
    LinearLayout llyHistory;
    @BindView(R.id.home_header_current_number)
    TextView tvLastEdNumber;


    @Inject
    HomeFragmentContract.Presenter presenter;

    private ShopListAdapter bottomAdapter;
    private final List<LotteryPic> bottomList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //绑定 ButterKnife
        ButterKnife.bind(this, view);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .build()
                .inject(this);

        presenter.setContractView(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        initHeaderTab();
        initCurrent();
        initLittleModuleRecyclerView();
        initSmartRefreshLayout();
    }

    private void initCurrent() {
        tvHistory.setOnClickListener(v -> {
            ToastUtils.show("历史记录");
            Intent intent = new Intent(this.getActivity(), HistoryActivity.class);
            this.startActivity(intent);
//                ToastUtils.show("历史记录");
        });
    }

    private void initHeaderTab() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtils.show(tab.getText() != null ? tab.getText().toString() : "");
                presenter.onTabChange(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    //初始化下拉刷新控件
    private void initSmartRefreshLayout() {
        smartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new CustomRefreshFooter(getActivity(), "加载中…"));
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.onLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.onRefresh();
            }
        });
    }

    @Override
    public void finishLoadmore(boolean success) {
        smartRefreshLayout.finishLoadmore(success);
    }

    @Override
    public void finishLoadmoreWithNoMoreData() {
        smartRefreshLayout.finishLoadmoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(boolean success) {
        smartRefreshLayout.finishRefresh(success);
    }

    @Override
    public void resetNoMoreData() {
        smartRefreshLayout.resetNoMoreData();
    }

    /**
     * 加载更多后添加新的数据到RecyclerView
     * @param shopModels
     */
    @Override
    public void addData2RecyclerView(List<ShopModel> shopModels) {

    }



    @SuppressLint("SetTextI18n")
    @Override
    public void displayLastedLotteryData(LotteryResp lastedLotteryResp) {
        Logger.d("最新的开奖信息:"+ new Gson().toJson(lastedLotteryResp));
        tvLastEdNumber.setText(String.valueOf(lastedLotteryResp.issueNumber));
        LinearLayout lly = (LinearLayout) llyLasted.getChildAt(1);
        int index = 0;
        for (int i = 0; i < lly.getChildCount(); i++) {
            WinningNumberInfo numberInfo = lastedLotteryResp.winningNumberInfo.get(index);
            index += 1;
            if (!(lly.getChildAt(i) instanceof LinearLayout)){
                index = index-1;
                continue;
            }
            LinearLayout llyChild = (LinearLayout) lly.getChildAt(i);
            TextView code = (TextView) llyChild.getChildAt(0);
            TextView info = (TextView) llyChild.getChildAt(1);
            code.setText(numberInfo.codeStr);
            code.setBackgroundResource(presenter.getResIdByColor(numberInfo.waveColor));
            info.setText(numberInfo.zodiac+"/"+numberInfo.fiveElementStr);
        }
        TextView tvNext = (TextView) llyLasted.getChildAt(2);
        tvNext.setText("第"+lastedLotteryResp.nextIssueNumber+"期  " +lastedLotteryResp.nextLotteryDateStr);
    }

    @Override
    public void displayLotteryHistoryData(LotteryHistoryResp lotteryHistoryResp) {
        Logger.d("最新的开奖信息历史:"+ new Gson().toJson(lotteryHistoryResp));
        for (int i = 0; i < 5; i++) {
            LotteryResp lotteryResp = lotteryHistoryResp.data.get(i);
            RelativeLayout rly = (RelativeLayout) llyHistory.getChildAt(i*2);
            LinearLayout rlyLly = (LinearLayout) rly.getChildAt(0);
            TextView rlyLlyTv3 = (TextView) rlyLly.getChildAt(1);
            rlyLlyTv3.setText(String.valueOf(lotteryResp.shortIssueNumber));
            TextView rlyTv = (TextView) rly.getChildAt(1);
            rlyTv.setText(lotteryResp.lotteryDateStr);
            LinearLayout llyChild = (LinearLayout) llyHistory.getChildAt(i*2+1);
            int index = 0;
            for (int j = 0; j < llyChild.getChildCount(); j++) {
                WinningNumberInfo numberInfo = lotteryResp.winningNumberInfo.get(index);
                index += 1;
                if (!(llyChild.getChildAt(j) instanceof LinearLayout)){
                    index = index-1;
                    continue;
                }
                LinearLayout llyLly = (LinearLayout) llyChild.getChildAt(j);
                TextView code = (TextView) llyLly.getChildAt(0);
                TextView info = (TextView) llyLly.getChildAt(1);
                code.setText(numberInfo.codeStr);
                code.setBackgroundResource(presenter.getResIdByColor(numberInfo.waveColor));
                info.setText(numberInfo.zodiac+"/"+numberInfo.fiveElementStr);
            }
        }
    }

    @Override
    public void displayLotteryHistoryDataFilter(LotteryHistoryResp lotteryHistoryResp) {
        Logger.d("最新的开奖信息历史带筛选:"+ new Gson().toJson(lotteryHistoryResp));
    }

    @Override
    public void displayLotteryPicListResp(LotteryPicListResp picListResp) {
        Logger.d("获取分页彩票图片资讯:"+ new Gson().toJson(picListResp));
//        bottomList.addAll(picListResp.data);
//        bottomAdapter.notifyItemInserted(bottomList.size());
    }
    @Override
    public void setRefreshFooter(RefreshFooter footer) {
        smartRefreshLayout.setRefreshFooter(footer);
    }

    /**
     * 下面的RecyclerView
     */
    private void initLittleModuleRecyclerView() {
        bottomAdapter = new ShopListAdapter(getActivity(),R.layout.home_bottom_item,bottomList);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        SpaceItemDecoration dividerItemDecoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(bottomAdapter);

        bottomAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ToastUtils.show(bottomList.get(position).name);
            Logger.d("");
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
