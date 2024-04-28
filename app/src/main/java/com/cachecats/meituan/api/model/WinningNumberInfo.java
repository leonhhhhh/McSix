package com.cachecats.meituan.api.model;

import java.util.Map;

public class WinningNumberInfo {
    public int code;
    public String codeStr;
    public int type;
    public int waveColor;
    public int fiveElement;
    public String zodiac;
    public String fiveElementStr;

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
