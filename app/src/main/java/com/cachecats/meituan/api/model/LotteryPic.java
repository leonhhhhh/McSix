package com.cachecats.meituan.api.model;

import java.util.Map;

class LotteryPic {
   private int year;
   private int id;
   private String name;
   private int issue;
   private String highSrc;
   private String lowSrc;
   private int height;
   private int width;
   private String type;

   public LotteryPic(int year, int id, String name, int issue, String highSrc, String lowSrc, int height, int width, String type) {
      this.year = year;
      this.id = id;
      this.name = name;
      this.issue = issue;
      this.highSrc = highSrc;
      this.lowSrc = lowSrc;
      this.height = height;
      this.width = width;
      this.type = type;
   }

   public void LotteryResult(Map<String, Object> data) {
      this.year = (int) data.get("year");
      this.id = (int) data.get("id");
      this.name = (String) data.get("name");
      this.issue = (int) data.get("issue");
      this.highSrc = (String) data.get("highSrc");
      this.lowSrc = (String) data.get("lowSrc");
      this.height = (int) data.get("height");
      this.width = (int) data.get("width");
      this.type = (String) data.get("type");
   }

   // Getters and setters omitted for brevity

   @Override
   public String toString() {
      return "LotteryPic{" +
              "year=" + year +
              ", id=" + id +
              ", name='" + name + '\'' +
              ", issue=" + issue +
              ", highSrc='" + highSrc + '\'' +
              ", lowSrc='" + lowSrc + '\'' +
              ", height=" + height +
              ", width=" + width +
              ", type='" + type + '\'' +
              '}';
   }
}
