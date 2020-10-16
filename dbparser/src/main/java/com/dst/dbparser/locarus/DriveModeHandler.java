package com.dst.dbparser.locarus;

public class DriveModeHandler /*extends Handler*/ {
    /** Handler must be interface **/
    private int first;
    private int second;
    private int third;

    public DriveModeHandler(double number) {
        first = DataParser.getNumberFromByte((int)number, 7, 1);
        second = DataParser.getNumberFromByte((int)number, 15, 1);
        third = DataParser.getNumberFromByte((int)number, 23, 1);
    }

    public double getMode(){
        return first + (second << 1) + (third << 2);
    }
}
