//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.date.model;

import java.util.List;

public class Year {
    private String startDay;
    private Long startDay13Time;
    private Long startDay10Time;
    private String lastDay;
    private Long lastDay13Time;
    private Long lastDay10Time;
    private List<String> monthStartDay;
    private List<Long> monthStartDay13Time;
    private List<Long> monthStartDay10Time;
    private List<String> monthEndDay;
    private List<Long> monthEndDay13Time;
    private List<Long> monthEndDay10Time;

    public Year() {
    }

    public List<Long> getMonthEndDay13Time() {
        return this.monthEndDay13Time;
    }

    public void setMonthEndDay13Time(List<Long> monthEndDay13Time) {
        this.monthEndDay13Time = monthEndDay13Time;
    }

    public List<Long> getMonthEndDay10Time() {
        return this.monthEndDay10Time;
    }

    public void setMonthEndDay10Time(List<Long> monthEndDay10Time) {
        this.monthEndDay10Time = monthEndDay10Time;
    }

    public List<String> getMonthEndDay() {
        return this.monthEndDay;
    }

    public void setMonthEndDay(List<String> monthEndDay) {
        this.monthEndDay = monthEndDay;
    }

    public List<Long> getMonthStartDay13Time() {
        return this.monthStartDay13Time;
    }

    public void setMonthStartDay13Time(List<Long> monthStartDay13Time) {
        this.monthStartDay13Time = monthStartDay13Time;
    }

    public List<Long> getMonthStartDay10Time() {
        return this.monthStartDay10Time;
    }

    public void setMonthStartDay10Time(List<Long> monthStartDay10Time) {
        this.monthStartDay10Time = monthStartDay10Time;
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

    public List<String> getMonthStartDay() {
        return this.monthStartDay;
    }

    public void setMonthStartDay(List<String> monthStartDay) {
        this.monthStartDay = monthStartDay;
    }

    public Long getLimitLastDay10Time() {
        return this.lastDay10Time + 86399L;
    }

    public Long getLimitLastDay13Time() {
        return this.lastDay13Time + 86399999L;
    }

    public String toString() {
        return "Year{startDay='" + this.startDay + '\'' + ", startDay13Time=" + this.startDay13Time + ", startDay10Time=" + this.startDay10Time + ", lastDay='" + this.lastDay + '\'' + ", lastDay13Time=" + this.lastDay13Time + ", lastDay10Time=" + this.lastDay10Time + ", monthStartDay=" + this.monthStartDay + ", monthStartDay13Time=" + this.monthStartDay13Time + ", monthStartDay10Time=" + this.monthStartDay10Time + '}';
    }
}
