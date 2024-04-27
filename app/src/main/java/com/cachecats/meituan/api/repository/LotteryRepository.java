package com.cachecats.meituan.api.repository;

import com.cachecats.meituan.api.ApiService;
import com.cachecats.meituan.api.RetrofitClient;
import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryPicListResp;
import com.cachecats.meituan.api.model.LotteryResp;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LotteryRepository {
    private ApiService apiService;

    @Inject
    public LotteryRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public Observable<LotteryResp> getLatestLotteryData(int type) {
        return apiService.getLotteryData(type);
    }

    public Observable<LotteryHistoryResp> getLotteryHistoryData(int type, int size) {
        return apiService.getLotteryHistoryData(type,size);
    }

    public Observable<LotteryHistoryResp> getLotteryHistoryDataFilter(int type, int size,int year) {
        return apiService.getLotteryHistoryDataFilter(type,size,year);
    }

    public Observable<LotteryPicListResp> getHomePicList(int type, int size, int page) {
        return apiService.getHomePicList(type,size,page);
    }
}

