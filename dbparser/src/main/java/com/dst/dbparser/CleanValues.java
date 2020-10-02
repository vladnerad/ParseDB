package com.dst.dbparser;

import com.dst.dbparser.fillers.DataFiller;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/* It is internal class, that can contain clean values from Locarus or database (except date and digitalIn).
* It's use for internal analyze.
* In some part of application (database) we can use SQL-syntax, if we don't need all parameters or number
* of rows is very big. There is structure of columns:
* 1 - JoyMoveF(+)/B(-)
* 2 - JoyMoveR(+)/L(-)
* 3 - PressLPump
* 4 - PressRPump
* see cleanDataColumnsNames */
public class CleanValues {

    private DataFiller filler;

    public CleanValues(DataFiller filler) {
        filler.fillAll(this);
    }

    public static final String dateName = "Date/Time";
    public static final HashMap<Integer, String> cleanDataColumnsNamesAnalogIn = new HashMap<>();
    public static final String errorsName = "Errors";
//    public static final HashMap<Integer, String> cleanDataColumnsNamesDigitalIn = new HashMap<>();

    public static final HashMap<String, Integer> cleanDataArrayIndex = new HashMap<>();

    public static final String tempHydOil = "HydOilTemp";
    public static final String tempCoolant = "CoolantTemp";
    public static final String tempGearbox = "GearboxTemp";
    public static final String tempTurbo = "TurboTemp";
    public static final String tempEnv = "EnvTemp";
    public static final String pressA = "PressA";
    public static final String pressB = "PressB";
    public static final String pressAttach = "PressAttach";
    public static final String pressFanDrive = "PressFanDrive";

    public static final String engineSpeed = "EngineSpeed";
    public static final String engSpeedTsc1 = "ReqEngSpeedPedal";
    public static final String maxJoy = "ReqEngSpeedJoy";
    public static final String accelPedal = "AccelPedal";

    static {

        cleanDataArrayIndex.put(tempHydOil, 0);
        cleanDataArrayIndex.put(tempCoolant, 1);
        cleanDataArrayIndex.put(tempGearbox, 2);
        cleanDataArrayIndex.put(tempTurbo, 3);
        cleanDataArrayIndex.put(tempEnv, 4);
        cleanDataArrayIndex.put(pressA, 5);
        cleanDataArrayIndex.put(pressB, 6);
        cleanDataArrayIndex.put(pressAttach, 7);
        cleanDataArrayIndex.put(pressFanDrive, 8);
        cleanDataArrayIndex.put(engineSpeed, 9);
        cleanDataArrayIndex.put(engSpeedTsc1, 10);
        cleanDataArrayIndex.put(maxJoy, 11);
        cleanDataArrayIndex.put(accelPedal, 12);

        cleanDataColumnsNamesAnalogIn.put(0, tempHydOil);
        cleanDataColumnsNamesAnalogIn.put(1, tempCoolant);
        cleanDataColumnsNamesAnalogIn.put(2, tempGearbox);
        cleanDataColumnsNamesAnalogIn.put(3, tempTurbo);
        cleanDataColumnsNamesAnalogIn.put(4, tempEnv);
        cleanDataColumnsNamesAnalogIn.put(5, pressA);
        cleanDataColumnsNamesAnalogIn.put(6, pressB);
        cleanDataColumnsNamesAnalogIn.put(7, pressAttach);
        cleanDataColumnsNamesAnalogIn.put(8, pressFanDrive);
        cleanDataColumnsNamesAnalogIn.put(9, engineSpeed);
        cleanDataColumnsNamesAnalogIn.put(10, engSpeedTsc1);
        cleanDataColumnsNamesAnalogIn.put(11, maxJoy);
        cleanDataColumnsNamesAnalogIn.put(12, accelPedal);

//        cleanDataColumnsNamesDigitalIn.put(0, "FanDriveAuto");
//        cleanDataColumnsNamesDigitalIn.put(1, "SafetyBtn");
//        cleanDataColumnsNamesDigitalIn.put(2, "TransportMode");
//        cleanDataColumnsNamesDigitalIn.put(3, "HydOilLowLev");
//        cleanDataColumnsNamesDigitalIn.put(4, "RipperMode");
//        cleanDataColumnsNamesDigitalIn.put(5, "SwimMode");
//        cleanDataColumnsNamesDigitalIn.put(6, "ParkingSwt");
//        cleanDataColumnsNamesDigitalIn.put(7, "TowingSwt");
    }

    private int[][] analogInCleanValues;
//    private boolean[][] digitalInValues;
//    private Set<Integer>[] errorsValues;
    private Instant[] dateValues;

    public int[][] getAnalogInCleanValues() {
        return analogInCleanValues;
    }

    public void setAnalogInCleanValues(int[][] analogInCleanValues) {
        this.analogInCleanValues = analogInCleanValues;
    }

//    public boolean[][] getDigitalInValues() {
//        return digitalInValues;
//    }

//    public void setDigitalInValues(boolean[][] digitalInValues) {
//        this.digitalInValues = digitalInValues;
//    }

//    public Set<Integer>[] getErrorsValues() {
//        return errorsValues;
//    }

//    public void setErrorsValues(Set<Integer>[] errorsValues) {
//        this.errorsValues = errorsValues;
//    }

    public Instant[] getDateValues() {
        return dateValues;
    }

    public void setDateValues(Instant[] dateValues) {
        this.dateValues = dateValues;
    }

    public boolean isDataCorrect(){
//        return analogInCleanValues.length == digitalInValues.length
//                && digitalInValues.length == errorsValues.length
//                && errorsValues.length == dateValues.length;
        return analogInCleanValues.length == dateValues.length;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        CleanValues cleanValues2 = (CleanValues) obj;
//        boolean isDateTimeEquals = Arrays.equals(this.dateValues, cleanValues2.dateValues);
//        boolean isAnalogInEquals = Arrays.deepEquals(this.analogInCleanValues, cleanValues2.analogInCleanValues);
//        boolean isDigitalInEquals = Arrays.deepEquals(this.digitalInValues, cleanValues2.digitalInValues);
//        boolean isErrorsValuesEquals = true;
//        if (this.errorsValues.length == cleanValues2.errorsValues.length){
//            for (int i = 0; i < errorsValues.length; i++){
//                if (!this.errorsValues[i].containsAll(cleanValues2.errorsValues[i])){
//                    isErrorsValuesEquals = false;
//                }
//            }
//        } else isErrorsValuesEquals = false;
//
//        return isDateTimeEquals && isAnalogInEquals && isDigitalInEquals && isErrorsValuesEquals;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Arrays.hashCode(analogInCleanValues);
//        result = 31 * result + Arrays.hashCode(digitalInValues);
//        result = 31 * result + Arrays.hashCode(errorsValues);
//        result = 31 * result + Arrays.hashCode(dateValues);
//        return result;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleanValues that = (CleanValues) o;
        return Objects.equals(filler, that.filler) &&
                Arrays.equals(analogInCleanValues, that.analogInCleanValues) &&
                Arrays.equals(dateValues, that.dateValues);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(filler);
        result = 31 * result + Arrays.hashCode(analogInCleanValues);
        result = 31 * result + Arrays.hashCode(dateValues);
        return result;
    }
}
