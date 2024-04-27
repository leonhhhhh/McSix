package com.cachecats.meituan.api;

import com.cachecats.meituan.api.model.LotteryResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
 @GET("open/lottery/latest")
 Observable<LotteryResp> getLotteryData(@Query("type") int type);
}

