package com.dst.dbparser.locarus;

public class MotorSpeedHandler {

    private int bigPart = -1;
    private int smallPart = -1;

    private boolean isTempMot;
    private double val;

    public MotorSpeedHandler(boolean isTempMot, double val) {
        this.isTempMot = isTempMot;
        this.val = val;
    }

    public double getSpeed(){
        if (isTempMot){
            bigPart = DataParser.getNumberFromByte((int) val, 16, 8);
            smallPart = DataParser.getNumberFromByte((int) val, 8, 2);
        } else {
            bigPart = DataParser.getNumberFromByte((int) val, 0, 8);
            smallPart = DataParser.getNumberFromByte((int) val, 14, 2);
        }
        int whole = smallPart << 8 | bigPart;
        return (double)(whole * 16 - 8000);
    }
}
