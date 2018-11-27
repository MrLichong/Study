//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date.model;

import java.util.List;

/**
 * 代表“季度”的实体类
 *
 * @author:LiChong
 * @date:2018/11/15
 */
public class Quarter {
    private String startDay;
    private Long startDay13Time;
    private Long startDay10Time;
    private String lastDay;
    private Long lastDay13Time;
    private Long lastDay10Time;
    private List<String> days;
    private List<Long> days13Time;
    private List<Long> days10Time;

    public Quarter() {
    }

    public String getStartDay() {
        return this.startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public Long getStartDay13Time() {
        return this.startDay13Time;
    }

    public void setStartDay13Time(Long startDay13Time) {
        this.startDay13Time = startDay13Time;
    }

    public Long getStartDay10Time() {
        return this.startDay10Time;
    }

    public void setStartDay10Time(Long startDay10Time) {
        this.startDay10Time = startDay10Time;
    }

    public String getLastDay() {
        return this.lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public Long getLastDay13Time() {
        return this.lastDay13Time;
    }

    public void setLastDay13Time(Long lastDay13Time) {
        this.lastDay13Time = lastDay13Time;
    }

    public Long getLastDay10Time() {
        return this.lastDay10Time;
    }

    public void setLastDay10Time(Long lastDay10Time) {
        this.lastDay10Time = lastDay10Time;
    }

    public List<String> getDays() {
        return this.days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<Long> getDays13Time() {
        return this.days13Time;
    }

    public void setDays13Time(List<Long> days13Time) {
        this.days13Time = days13Time;
    }

    public List<Long> getDays10Time() {
        return this.days10Time;
    }

    public void setDays10Time(List<Long> days10Time) {
        this.days10Time = days10Time;
    }
}
