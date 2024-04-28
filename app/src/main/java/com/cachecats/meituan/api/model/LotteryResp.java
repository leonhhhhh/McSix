package com.cachecats.meituan.api.model;

import java.util.List;

public class LotteryResp {
    public int shortIssue;
    public int nestShortIssue;
    public int issueNumber;
    public long nextLotteryDate;
    public int nextIssueNumber;
    public String nextLotteryDateStr;
    public int type;
    public List<String> winningNumber;
    public int tNum;
    public List<Integer> pNum;
    public String replayVideo;
    public List<WinningNumberInfo> winningNumberInfo;
    public int year;

    public int id;
    public int shortIssueNumber;
    public long lotteryDate;
    public String lotteryDateStr;
    public LotteryResp(int shortIssue, int nestShortIssue, int issueNumber, long nextLotteryDate, int nextIssueNumber, String nextLotteryDateStr, int type, List<String> winningNumber, int tNum, List<Integer> pNum, String replayVideo, List<WinningNumberInfo> winningNumberInfo, int year, int id, int shortIssueNumber, long lotteryDate, String lotteryDateStr) {
        this.shortIssue = shortIssue;
        this.nestShortIssue = nestShortIssue;
        this.issueNumber = issueNumber;
        this.nextLotteryDate = nextLotteryDate;
        this.nextIssueNumber = nextIssueNumber;
        this.nextLotteryDateStr = nextLotteryDateStr;
        this.type = type;
        this.winningNumber = winningNumber;
        this.tNum = tNum;
        this.pNum = pNum;
        this.replayVideo = replayVideo;
        this.winningNumberInfo = winningNumberInfo;
        this.year = year;
        this.id = id;
        this.shortIssueNumber = shortIssueNumber;
        this.lotteryDate = lotteryDate;
        this.lotteryDateStr = lotteryDateStr;
    }



    @Override
    public String toString() {
        return "LotteryResp{" +
                "shortIssue=" + shortIssue +
                ", nestShortIssue=" + nestShortIssue +
                ", issueNumber=" + issueNumber +
                ", nextLotteryDate=" + nextLotteryDate +
                ", nextIssueNumber=" + nextIssueNumber +
                ", nextLotteryDateStr='" + nextLotteryDateStr + '\'' +
                ", type=" + type +
                ", winningNumber=" + winningNumber +
                ", tNum=" + tNum +
                ", pNum=" + pNum +
                ", replayVideo='" + replayVideo + '\'' +
                ", winningNumberInfo=" + winningNumberInfo +
                ", year=" + year +
                '}';
    }
}
