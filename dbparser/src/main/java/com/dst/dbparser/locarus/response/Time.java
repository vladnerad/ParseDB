package com.dst.dbparser.locarus.response;

import java.time.Instant;

public class Time implements Comparable{

    private String value;

    public Time(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        Instant o1 = Instant.parse(this.value);
        Time time = (Time)o;
        Instant o2 = Instant.parse(time.value);
        return o1.compareTo(o2);
    }
}
