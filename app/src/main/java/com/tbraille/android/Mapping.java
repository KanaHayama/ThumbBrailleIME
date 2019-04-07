package com.tbraille.android;

import java.util.HashMap;

public class Mapping {

    private enum State {
        Normal, Capital, Number,
    }

    public final class MapResult {
        public final String text;
        public final String voice;
        public MapResult(String text, String voice) {
            this.text = text;
            this.voice = voice;
        }
    }

    private static final int CAPITAL_SIGN = Integer.valueOf("100000",2).intValue();
    private static final int NUMBER_SIGN = Integer.valueOf("111100",2).intValue();

    private static final String CAPITAL_PREFIX = "Capital ";
    private static final String NORMAL_VOICE = "Normal follows";
    private static final String CAPITAL_VOICE = "Capital follows";
    private static final String NUMBER_VOICE = "Number follows";
    private static final String NULL_VOICE = "Wrong input";

    private  State state;

    private HashMap<Integer, String> normalMap;
    private HashMap<Integer, String> capitalMap;
    private HashMap<Integer, String> numberMap;

    public Mapping() {
        state = State.Normal;
        createMaps();
    }

    private void createMaps() {
        normalMap = new HashMap<Integer, String>();

        normalMap.put(Integer.valueOf("000001",2), "a");
        normalMap.put(Integer.valueOf("000011",2), "b");
        normalMap.put(Integer.valueOf("001001",2), "c");
        normalMap.put(Integer.valueOf("011001",2), "d");
        normalMap.put(Integer.valueOf("010001",2), "e");
        normalMap.put(Integer.valueOf("001011",2), "f");
        normalMap.put(Integer.valueOf("011011",2), "g");
        normalMap.put(Integer.valueOf("010011",2), "h");
        normalMap.put(Integer.valueOf("001010",2), "i");
        normalMap.put(Integer.valueOf("011010",2), "j");
        normalMap.put(Integer.valueOf("000101",2), "k");
        normalMap.put(Integer.valueOf("000111",2), "l");
        normalMap.put(Integer.valueOf("001101",2), "m");
        normalMap.put(Integer.valueOf("011101",2), "n");
        normalMap.put(Integer.valueOf("010101",2), "o");
        normalMap.put(Integer.valueOf("001111",2), "p");
        normalMap.put(Integer.valueOf("011111",2), "q");
        normalMap.put(Integer.valueOf("010111",2), "r");
        normalMap.put(Integer.valueOf("001110",2), "s");
        normalMap.put(Integer.valueOf("011110",2), "t");
        normalMap.put(Integer.valueOf("100101",2), "u");
        normalMap.put(Integer.valueOf("100111",2), "v");
        normalMap.put(Integer.valueOf("101101",2), "x");
        normalMap.put(Integer.valueOf("111101",2), "y");
        normalMap.put(Integer.valueOf("110101",2), "z");
        normalMap.put(Integer.valueOf("111010",2), "w");
        normalMap.put(Integer.valueOf("100001",2), "ch");
        normalMap.put(Integer.valueOf("101001",2), "sh");
        normalMap.put(Integer.valueOf("111001",2), "th");
        normalMap.put(Integer.valueOf("000010",2), ",");
        normalMap.put(Integer.valueOf("000110",2), ";");
        normalMap.put(Integer.valueOf("000100",2), "'");
        normalMap.put(Integer.valueOf("010010",2), ":");
        normalMap.put(Integer.valueOf("100100",2), "-");
        normalMap.put(Integer.valueOf("101000",2), ".");
        normalMap.put(Integer.valueOf("110010",2), ".");
        normalMap.put(Integer.valueOf("010110",2), "!");
        normalMap.put(Integer.valueOf("100110",2), "?");
        normalMap.put(Integer.valueOf("001100",2), "\\");

        capitalMap = new HashMap<Integer, String>();

        capitalMap.put(Integer.valueOf("000001",2), "A");
        capitalMap.put(Integer.valueOf("000011",2), "B");
        capitalMap.put(Integer.valueOf("001001",2), "C");
        capitalMap.put(Integer.valueOf("011001",2), "D");
        capitalMap.put(Integer.valueOf("010001",2), "E");
        capitalMap.put(Integer.valueOf("001011",2), "F");
        capitalMap.put(Integer.valueOf("011011",2), "G");
        capitalMap.put(Integer.valueOf("010011",2), "H");
        capitalMap.put(Integer.valueOf("001010",2), "I");
        capitalMap.put(Integer.valueOf("011010",2), "J");
        capitalMap.put(Integer.valueOf("000101",2), "K");
        capitalMap.put(Integer.valueOf("000111",2), "L");
        capitalMap.put(Integer.valueOf("001101",2), "M");
        capitalMap.put(Integer.valueOf("011101",2), "N");
        capitalMap.put(Integer.valueOf("010101",2), "O");
        capitalMap.put(Integer.valueOf("001111",2), "P");
        capitalMap.put(Integer.valueOf("011111",2), "Q");
        capitalMap.put(Integer.valueOf("010111",2), "R");
        capitalMap.put(Integer.valueOf("001110",2), "S");
        capitalMap.put(Integer.valueOf("011110",2), "T");
        capitalMap.put(Integer.valueOf("100101",2), "U");
        capitalMap.put(Integer.valueOf("100111",2), "V");
        capitalMap.put(Integer.valueOf("101101",2), "X");
        capitalMap.put(Integer.valueOf("111101",2), "Y");
        capitalMap.put(Integer.valueOf("110101",2), "Z");
        capitalMap.put(Integer.valueOf("111010",2), "W");
        capitalMap.put(Integer.valueOf("100001",2), "CH");
        capitalMap.put(Integer.valueOf("101001",2), "SH");
        capitalMap.put(Integer.valueOf("111001",2), "TH");
        capitalMap.put(Integer.valueOf("000010",2), ",");
        capitalMap.put(Integer.valueOf("000110",2), ";");
        capitalMap.put(Integer.valueOf("000100",2), "'");
        capitalMap.put(Integer.valueOf("010010",2), ":");
        capitalMap.put(Integer.valueOf("100100",2), "-");
        capitalMap.put(Integer.valueOf("101000",2), ".");
        capitalMap.put(Integer.valueOf("110010",2), ".");
        capitalMap.put(Integer.valueOf("010110",2), "!");
        capitalMap.put(Integer.valueOf("100110",2), "?");
        capitalMap.put(Integer.valueOf("001100",2), "\\");


        numberMap = new HashMap<Integer, String>();

        numberMap.put(Integer.valueOf("000001",2), "1");
        numberMap.put(Integer.valueOf("000011",2), "2");
        numberMap.put(Integer.valueOf("001001",2), "3");
        numberMap.put(Integer.valueOf("011001",2), "4");
        numberMap.put(Integer.valueOf("010001",2), "5");
        numberMap.put(Integer.valueOf("001011",2), "6");
        numberMap.put(Integer.valueOf("011011",2), "7");
        numberMap.put(Integer.valueOf("010011",2), "8");
        numberMap.put(Integer.valueOf("001010",2), "9");
        numberMap.put(Integer.valueOf("011010",2), "0");
        numberMap.put(16, ",");
        numberMap.put(48, ";");
        numberMap.put(32, "'");
        numberMap.put(18, ":");
        numberMap.put(36, "-");
        numberMap.put(5, ".");
        numberMap.put(22, ".");
        numberMap.put(50, "!");
        numberMap.put(52, "?");
        numberMap.put(33, "\\");
    }

    public MapResult getMapping(int braille) {
        if (braille == CAPITAL_SIGN) {
            if (state == State.Capital) {
                state = State.Normal;
                return new MapResult("", NORMAL_VOICE);
            } else {
                state = State.Capital;
                return new MapResult("", CAPITAL_VOICE);
            }
        } else if (braille == NUMBER_SIGN) {
            if (state == State.Number) {
                state = State.Normal;
                return new MapResult("", NORMAL_VOICE);
            } else {
                state = State.Number;
                return new MapResult("", NUMBER_VOICE);
            }
        } else {
            String text;
            switch (state) {
                case Normal:
                    if (normalMap.containsKey(braille)) {
                        text = normalMap.get(braille);
                        return new MapResult(text, text);
                    }
                    break;
                case Capital:
                    if (capitalMap.containsKey(braille)) {
                        text = capitalMap.get(braille);
                        return new MapResult(text, CAPITAL_PREFIX + text);
                    }
                    break;
                case Number:
                    if (numberMap.containsKey(braille)) {
                        text = numberMap.get(braille);
                        return new MapResult(text, text);
                    }
                    break;
            }
            return new MapResult("", NULL_VOICE);
        }
    }
}
