package com.cachecats.meituan.api.model;

import java.util.List;

public class LotteryPicListResp extends BasePageResp{
    public List<LotteryPic> data; // Using a non-generic List instead of List<T>

    public LotteryPicListResp(int page, String size, int total, List<LotteryPic> data) {
        super(page, size, total);
        this.data = data;
    }

    @Override
    public String toString() {
        return "LotteryPicListResp{" +
                "data=" + data +
                '}';
    }
}
