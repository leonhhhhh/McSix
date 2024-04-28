package com.cachecats.meituan.api;

import com.cachecats.meituan.api.model.LotteryHistoryResp;
import com.cachecats.meituan.api.model.LotteryPicListResp;
import com.cachecats.meituan.api.model.LotteryResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
 @GET("open/lottery/latest")
 Observable<LotteryResp> getLotteryData(@Query("type") int type);

 @GET("open/lottery/history")
 Observable<LotteryHistoryResp> getLotteryHistoryData(@Query("type") int type, @Query("size") int size);

 @GET("open/lottery/history")
 Observable<LotteryHistoryResp> getLotteryHistoryDataFilter(@Query("type") int type, @Query("size") int size
         ,@Query("year") int year,@Query("page") int page,@Query("sort") int sort,@Query("psort") int psort);

 @GET("open/pic/list")
 Observable<LotteryPicListResp> getHomePicList(@Query("type") int type, @Query("size") int size
         , @Query("page") int page);
}

