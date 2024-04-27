package com.cachecats.meituan.api.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotteryResp {
    private int shortIssue;
    private int nestShortIssue;
    private int issueNumber;
    private long nextLotteryDate;
    private int nextIssueNumber;
    private String nextLotteryDateStr;
    private int type;
    private List<String> winningNumber;
    private int tNum;
    private List<Integer> pNum;
    private String replayVideo;
    private List<WinningNumberInfo> winningNumberInfo;
    private int year;
    public LotteryResp(int shortIssue, int nestShortIssue, int issueNumber, long nextLotteryDate, int nextIssueNumber, String nextLotteryDateStr, int type, List<String> winningNumber, int tNum, List<Integer> pNum, String replayVideo, List<WinningNumberInfo> winningNumberInfo, int year) {
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
    }

    // Getters and setters omitted for brevity
    public static LotteryResp fromJson(String json) {
        // Parse JSON data into a map
        Map<String, Object> data = new Gson().fromJson(json, Map.class);

        // Convert map values to appropriate types
        int shortIssue = (int) data.get("shortIssue");
        int nestShortIssue = (int) data.get("nestShortIssue");
        int issueNumber = (int) data.get("issueNumber");
        long nextLotteryDate = (long) data.get("nextLotteryDate");
        int nextIssueNumber = (int) data.get("nextIssueNumber");
        String replayVideo = (String) data.get("replayVideo");
        String nextLotteryDateStr = (String) data.get("nextLotteryDateStr");
        int type = (int) data.get("type");
        int tNum = (int) data.get("tNum");

        // Convert winningNumber list to String list
        List<String> winningNumber = (List<String>) data.get("winningNumber");

        // Convert tNum and pNum lists to Integer lists
        List<Integer> pNum = new ArrayList<>();
        for (Object num : (List<?>) data.get("pNum")) {
            pNum.add((Integer) num);
        }

        // Convert winningNumberInfo list to WinningNumberInfo list
        List<WinningNumberInfo> winningNumberInfo = new ArrayList<>();
        for (Map<String, Object> infoMap : (List<Map<String, Object>>) data.get("winningNumberInfo")) {
            winningNumberInfo.add(new WinningNumberInfo(infoMap));
        }

        int year = (int) data.get("year");

        // Create and return LotteryResult object
        return new LotteryResp(shortIssue, nestShortIssue, issueNumber, nextLotteryDate, nextIssueNumber, nextLotteryDateStr, type, winningNumber, tNum, pNum, replayVideo, winningNumberInfo, year);
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

    public static class WinningNumberInfo {

        private int code;
        private String codeStr;
        private int type;
        private int waveColor;
        private int fiveElement;
        private String zodiac;
        private String fiveElementStr;

        public WinningNumberInfo(int code, String codeStr, int type, int waveColor, int fiveElement, String zodiac, String fiveElementStr) {
            this.code = code;
            this.codeStr = codeStr;
            this.type = type;
            this.waveColor = waveColor;
            this.fiveElement = fiveElement;
            this.zodiac = zodiac;
            this.fiveElementStr = fiveElementStr;
        }

        public WinningNumberInfo(Map<String, Object> data) {
            this.code = (int) data.get("code");
            this.codeStr = (String) data.get("codeStr");
            this.type = (int) data.get("type");
            this.waveColor = (int) data.get("waveColor");
            this.fiveElement = (int) data.get("fiveElement");
            this.zodiac = (String) data.get("zodiac");
            this.fiveElementStr = (String) data.get("fiveElementStr");
        }

        // Getters and setters omitted for brevity

        @Override
        public String toString() {
            return "WinningNumberInfo{" +
                    "code=" + code +
                    ", codeStr='" + codeStr + '\'' +
                    ", type=" + type +
                    ", waveColor=" + waveColor +
                    ", fiveElement=" + fiveElement +
                    ", zodiac='" + zodiac + '\'' +
                    ", fiveElementStr='" + fiveElementStr + '\'' +
                    '}';
        }
    }
}
