package com.cachecats.meituan.app.home;

import android.content.Context;

import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryPicListResp;
import com.cachecats.meituan.api.model.LotteryResp;
import com.cachecats.meituan.api.repository.LotteryRepository;
import com.cachecats.meituan.constants.LotteryType;
import com.solo.common.rxjava.CloseableRxServiceExecutor;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by solo on 2018/1/10.
 */

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter {
    private HomeFragmentContract.View mFragment;
    private final Context mContext;
    private final CloseableRxServiceExecutor executor;

    //每页的大小
    private static final int PAGE_SIZE = 10;
    //当前是第几页
    private int mCurrentPage = 0;
    private int mTab = 0;
    //是否没有更多数据了
    private boolean isNoMoreData = false;


    private final LotteryRepository lotteryRepository;

    @Inject
    public HomeFragmentPresenter(Context context,
                                 CloseableRxServiceExecutor executor, LotteryRepository lotteryRepository) {
        mContext = context;
        this.executor = executor;
        this.lotteryRepository = lotteryRepository;
    }

    @Override
    public void setContractView(HomeFragmentContract.View fragment) {
        mFragment = fragment;
    }

    @Override
    public void onStart() {
//        mockUtils.mockShopDataToDB();
//        mockUtils.clearShop();
//        mockUtils.mockGroupPackagesToDB();
//        getAllShops();
//        mockUtils.mockGroupInfoData();

        init();
    }

    @Override
    public void onTabChange(int tab) {
        init();
    }

    private void init() {
        initLastedLotteryData();
        initLotteryHistoryData();
        initLotteryPicList();
    }

    private void initLotteryPicList() {
        getLotteryPicList();
    }

    private void initLotteryHistoryData() {
        getLotteryHistoryData();
    }

    private void initLastedLotteryData() {
        getLatestLotteryData();
    }


    @Override
    public void onDestroy() {
        mCurrentPage = 0;
        isNoMoreData = false;
    }


    @Override
    public void onLoadMore() {
        if (isNoMoreData) {
            return;
        }

        mCurrentPage++;
//        executor.execute(
//                shopService.getShopsByPage(mCurrentPage, PAGE_SIZE),
//                shopModels -> {
//                    Logger.d(shopModels);
//                    //返回结果为空则说明没有更多数据了
//                    if (shopModels.isEmpty()) {
//                        isNoMoreData = true;
//                        //重置Footer为没有更多数据状态
//                        mFragment.setRefreshFooter(new CustomRefreshFooter(mContext, "没有更多啦"));
//                        mFragment.finishLoadmoreWithNoMoreData();
//                        return;
//                    }
//                    mFragment.addData2RecyclerView(shopModels);
//                    mFragment.finishLoadmore(true);
//                },
//                error -> {
//                    Logger.d(error);
//                    mFragment.finishLoadmore(false);
//                });
    }

    @Override
    public void onRefresh() {
//        mCurrentPage = 0;
//        isNoMoreData = false;
//        mFragment.setRefreshFooter(new CustomRefreshFooter(mContext, "加载中…"));
//        //重置没有更多数据状态
//        mFragment.resetNoMoreData();
//        executor.execute(
//                shopService.getShopsByPage(0, PAGE_SIZE),
//                shopModels -> {
//                    Logger.d(shopModels);
//                    mFragment.setShopListData(shopModels);
//                    mFragment.finishRefresh(true);
//                },
//                error -> {
//                    mFragment.finishRefresh(false);
//                });
    }

    @Override
    public void getLatestLotteryData() {
        lotteryRepository.getLatestLotteryData(getType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LotteryResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LotteryResp responseObject) {
                        mFragment.displayLastedLotteryData(responseObject);
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

    @Override
    public void getLotteryHistoryData() {
        lotteryRepository.getLotteryHistoryData(getType(),5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LotteryHistoryResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LotteryHistoryResp responseObject) {
                        mFragment.displayLotteryHistoryData(responseObject);
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

    @Override
    public void getLotteryHistoryDataFilter() {
        lotteryRepository.getLotteryHistoryDataFilter(getType(),5,2024)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LotteryHistoryResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LotteryHistoryResp responseObject) {
                        mFragment.displayLotteryHistoryData(responseObject);
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

    @Override
    public void getLotteryPicList() {
        lotteryRepository.getHomePicList(getType(),10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LotteryPicListResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LotteryPicListResp responseObject) {
                        mFragment.displayLotteryPicListResp(responseObject);
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
    private int getType(){
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
