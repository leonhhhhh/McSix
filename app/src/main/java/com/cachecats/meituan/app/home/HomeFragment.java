package com.cachecats.meituan.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cachecats.domin.shop.model.ShopModel;
import com.cachecats.meituan.MyApplication;
import com.cachecats.meituan.R;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.app.HistoryActivity;
import com.cachecats.meituan.app.home.adapter.LittleModuleAdapter;
import com.cachecats.meituan.app.home.model.IconTitleModel;
import com.cachecats.meituan.base.BaseFragment;
import com.cachecats.meituan.di.components.DaggerActivityComponent;
import com.cachecats.meituan.utils.ToastUtils;
import com.cachecats.meituan.widget.IconTitleView;
import com.cachecats.meituan.widget.decoration.HomeGridDecoration;
import com.cachecats.meituan.widget.refresh.CustomRefreshFooter;
import com.cachecats.meituan.widget.refresh.CustomRefreshHeader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by solo on 2018/1/8.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {


    //小模块GridView布局
    @BindView(R.id.recyclerview_little_module)
    RecyclerView littleModuleRecyclerView;
    //下拉刷新组件
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.home_header_tab)
    TabLayout tabLayout;
    @BindView(R.id.home_header_current_history)
    TextView tvHistory;

    @Inject
    HomeFragmentContract.Presenter presenter;

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

    @Override
    public void displayLastedLotteryData(LotteryResp lastedLotteryResp) {
        Logger.d("最新的开奖信息:"+ new Gson().toJson(lastedLotteryResp));
    }

    @Override
    public void setRefreshFooter(RefreshFooter footer) {
        smartRefreshLayout.setRefreshFooter(footer);
    }


    @Override
    public void setShopListData(List<ShopModel> shopModels) {
    }


    /**
     * 初始化小模块的RecyclerView
     */
    private void initLittleModuleRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        //设置LayoutManager
        littleModuleRecyclerView.setLayoutManager(gridLayoutManager);
        //设置分割器
        littleModuleRecyclerView.addItemDecoration(new HomeGridDecoration(12));
        //设置动画
        littleModuleRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置Adapter
        List<IconTitleModel> iconTitleModels = presenter.getIconTitleModels();
        LittleModuleAdapter littleModuleAdapter = new LittleModuleAdapter(
                R.layout.view_icon_title_small, iconTitleModels);

        littleModuleRecyclerView.setAdapter(littleModuleAdapter);
        //设置item点击事件
        littleModuleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.show(iconTitleModels.get(position).getTitle());
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    /**
     * 往根布局上添加View
     */
    @Override
    public void addViewToBigModule(IconTitleView iconTitleView) {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
