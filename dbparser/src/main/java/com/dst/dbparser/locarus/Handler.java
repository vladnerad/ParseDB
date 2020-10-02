package com.dst.dbparser.locarus;

public class Handler {
//    private String name;
    private int startBit;
    private int length;
    private double multiply;
    private int shift;

    public Handler(/*String name,*/ int startBit, int length, double multiply, int shift) {
//        this.name = name;
        this.startBit = startBit;
        this.length = length;
        this.multiply = multiply;
        this.shift = shift;
    }

//    public String getName() {
//        return name;
//    }

    public int getStartBit() {
        return startBit;
    }

    public int getLength() {
        return length;
    }

    public double getMultiply() {
        return multiply;
    }

    public int getShift() {
        return shift;
    }
}
