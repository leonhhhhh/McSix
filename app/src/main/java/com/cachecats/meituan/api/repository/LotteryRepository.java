package com.cachecats.meituan.api.repository;

import com.cachecats.meituan.api.ApiService;
import com.cachecats.meituan.api.RetrofitClient;
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
}

