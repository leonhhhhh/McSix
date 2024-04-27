package com.cachecats.meituan.api.model;

import java.util.List;

public class LotteryHistoryResp extends BasePageResp{
    private List<LotteryResp> data; // Using a non-generic List instead of List<T>

    public LotteryHistoryResp(int page, String size, int total, List<LotteryResp> data) {
        super(page, size, total);
        this.data = data;
    }
}
