package com.dst.dbparser.fillers;

import com.dst.dbparser.CleanValues;
import com.dst.dbparser.locarus.*;
import com.dst.dbparser.locarus.response.LocarusDataField;
import com.dst.dbparser.locarus.response.Time;

import java.time.Instant;
import java.util.*;

public class LocarusDataFiller implements DataFiller{

    public static final int ANALOG_IN_NUMBER = 16;
//    private CleanValues cleanValues;
    private List<LocarusDataField> data;
    private RawLocarusDataAnalogIn rawLocarusDataAnalogIn;
    private HashMap<String, Handler> handlers;

    public LocarusDataFiller(/*CleanValues cleanValues,*/ List<LocarusDataField> data) {
//        this.cleanValues = cleanValues;
        this.data = data;
        this.rawLocarusDataAnalogIn = new RawLocarusDataAnalogIn();
        handlers = new HandlersStorage().getHandlers();

        rawLocarusDataAnalogIn.setAnalogIn(getDataArray(data));
    }

    @Override
    public void fillDate(CleanValues cleanValues) {
        cleanValues.setDateValues(
                data.stream()
                        .map(LocarusDataField::getTime)
                        .map(Time::toString)
                        .map(Instant::parse)
                        .toArray(Instant[]::new));
    }

    @Override
    public void fillAnalogIn(CleanValues cleanValues) {
        int[][] result = new int[rawLocarusDataAnalogIn.getAnalogIn().length][CleanValues.cleanDataArrayIndex.size()];
        for (int i = 0; i < rawLocarusDataAnalogIn.getAnalogIn().length; i++){
            result[i] = convertDataLine(rawLocarusDataAnalogIn.getAnalogIn()[i]);
        }
        cleanValues.setAnalogInCleanValues(result);
    }

    @Override
    public void fillErrors(CleanValues cleanValues) {
//        Set<Integer>[] result = new Set[rawLocarusDataAnalogIn.getAnalogIn().length];
////        for (int i = 0; i < rawLocarusDataAnalogIn.getAnalogIn().length; i++) {
////            result[i] = getErrorsFromRow(rawLocarusDataAnalogIn.getAnalogIn()[i]);
////        }
////        cleanValues.setErrorsValues(result);
        System.out.println("public void fillErrors(CleanValues cleanValues) {");
    }

    @Override
    public void fillDigitalIn(CleanValues cleanValues) {
//        cleanValues.setDigitalInValues(DataParser.getDigitalInData(data));
        System.out.println("public void fillDigitalIn(CleanValues cleanValues)");
    }

    @Override
    public void fillAll(CleanValues cleanValues) {
        fillDate(cleanValues);
        fillAnalogIn(cleanValues);
        fillErrors(cleanValues);
        fillDigitalIn(cleanValues);
    }

    //converts one raw data line to clear values
    private int[] convertDataLine(int[] rawDataLine) {
        int[] result = new int[CleanValues.cleanDataArrayIndex.size()];
        for (int i = 0; i < ANALOG_IN_NUMBER; i++) {
            //16 channels in Locarus
            if (i == LocarusChannels.P1.ordinal()) {
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.joyY)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.joyY));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.joyX)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.joyX));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.tempHydOil)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.tempHydOil));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.fuelLevel)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.fuelLevel));
                continue;
            }
            if (i == LocarusChannels.P2.ordinal()) {
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.engineSpeed)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.engineSpeed));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.engSpeedTsc1)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.engSpeedTsc1));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.maxJoy)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.maxJoy));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.accelPedal)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.accelPedal));
                continue;
            }
            if (i == LocarusChannels.P3.ordinal()) {
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.tempCoolant)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.tempCoolant));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.tempGearbox)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.tempGearbox));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.tempTurbo)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.tempTurbo));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.tempEnv)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.tempEnv));
                continue;
            }
            if (i == LocarusChannels.P6.ordinal()) {
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.pressA)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.pressA));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.pressB)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.pressB));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.pressAttach)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.pressAttach));
                result[CleanValues.cleanDataArrayIndex.get(CleanValues.pressFanDrive)] = DataParser.getNumberFromByte(rawDataLine[i], handlers.get(CleanValues.pressFanDrive));
//                continue;
            }
        }
        return result;
    }

//    public static Set<Integer> getErrorsFromRow(int[] row) {
//        Set<Integer> result = new TreeSet<>();
//        result.addAll(getErrorsFromNumber(row[LocarusChannels.ERR_PACK_1.ordinal()], 1));
//        result.addAll(getErrorsFromNumber(row[LocarusChannels.ERR_PACK_2.ordinal()], 2));
//        result.addAll(getErrorsFromNumber(row[LocarusChannels.ERR_PACK_3.ordinal()], 3));
//        return result;
//    }

    public static Set<Integer> getErrorsFromNumber(int n, int errPackNum) {
        Set<Integer> result = new HashSet<>();
        boolean[] bits = new boolean[32];
        for (int i = 31; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                if (errPackNum == 1) result.add(i + 1);
                else if (errPackNum == 2) result.add(i + 33);
                else if (errPackNum == 3) result.add(i + 65);
                else System.out.println("getErrorsFromNumber error");
            }
        }
        return result;
    }
//    public static Set<Integer> getErrors(int errPack1, int errPack2, int errPack3) {
//        Set<Integer> result = new TreeSet<>();
//        result.addAll(getErrorsFromNumber(errPack1, 1));
//        result.addAll(getErrorsFromNumber(errPack2, 2));
//        result.addAll(getErrorsFromNumber(errPack3, 3));
//        return result;
//    }

    public static int[][] getDataArray(List<LocarusDataField> data) {
        try {
            int[][] result = new int[data.size()][ANALOG_IN_NUMBER];
            for (int i = 0; i < result.length; i++) {
                Map<String, Double> map = data.get(i).getAnalogIn();
                for (int j = 0; j < result[0].length; j++) {
                    if(map.containsKey(String.valueOf(j + 1))) {
                        result[i][j] = (int) (double) map.get(String.valueOf(j + 1));
                    } else result[i][j] = 999999999; //error, data absent
                }
            }
            return result;
        } catch (IndexOutOfBoundsException e){
//            System.out.println("DataParser - getDataArray - empty");
            return null;
        }
    }
}