package com.cachecats.meituan.app.home;

import com.cachecats.domin.shop.model.ShopModel;
import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryPicListResp;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.base.BasePresenter;
import com.scwang.smartrefresh.layout.api.RefreshFooter;

import java.util.List;

/**
 * Created by solo on 2018/1/10.
 */

public interface HomeFragmentContract {

    interface View {


        void finishLoadmore(boolean success);

        void finishLoadmoreWithNoMoreData();

        void resetNoMoreData();

        void finishRefresh(boolean success);

        void setRefreshFooter(RefreshFooter footer);

        void addData2RecyclerView(List<ShopModel> shopModels);

        void displayLastedLotteryData(LotteryResp lastedLotteryResp);
        void displayLotteryHistoryData(LotteryHistoryResp lotteryHistoryResp);
        void displayLotteryHistoryDataFilter(LotteryHistoryResp lotteryHistoryResp);
        void displayLotteryPicListResp(LotteryPicListResp picListResp);
    }

    interface Presenter extends BasePresenter<View>{
        void onLoadMore();

        void onRefresh();
        void getLatestLotteryData();
        void getLotteryHistoryData();
        void getLotteryHistoryDataFilter();
        void getLotteryPicList();
        void onTabChange(int tab);
        int getResIdByColor(int waveColor);
    }
}
