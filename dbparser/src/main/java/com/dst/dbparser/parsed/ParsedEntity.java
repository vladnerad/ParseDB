package com.dst.dbparser.parsed;

import com.dst.dbparser.locarus.response.Time;

import java.util.Map;

public class ParsedEntity {

    private Time time;
    private Map<String, Double> params;

    public void setParams(Map<String, Double> params) {
        this.params = params;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }
}