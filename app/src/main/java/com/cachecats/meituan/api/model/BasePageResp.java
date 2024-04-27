package com.cachecats.meituan.api.model;

public class BasePageResp {
    private int page;
    private String size;
    private int total;

    public BasePageResp(int page, String size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "LotteryHistoryResp{" +
                "page=" + page +
                ", size='" + size + '\'' +
                ", total=" + total +
                '}';
    }
}
