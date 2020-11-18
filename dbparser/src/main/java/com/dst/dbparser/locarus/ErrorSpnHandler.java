package com.dst.dbparser.locarus;

public class ErrorSpnHandler {

    private int spnP1 = -1;
    private int spnP2 = -1;
    private int spnP3 = -1;
    private int fmi = -1;

    public void setSpn1(int spn1) {
        this.spnP1 = spn1;
    }

    public void setSpn2(int spn2) {
        this.spnP2 = spn2;
    }

    public void setSpn3(int spn3) {
        this.spnP3 = spn3;
    }

    public void setFmi(int fmi) {
        this.fmi = fmi;
    }

    public double getErrCode(){
        if (spnP1 != -1
                && spnP2 != -1
                && spnP3 != -1
                && fmi != -1) {

            int spn = (spnP3 << 16) + (spnP2 << 8) + (spnP1);
            return spn + ((double)fmi / 100);
        }
        return 0;
    }
}
