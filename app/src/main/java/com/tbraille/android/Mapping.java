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

    private static final int CAPITAL_SIGN = 4;
    private static final int NUMBER_SIGN = 39;

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
        normalMap.put(8, "a");
        normalMap.put(24, "b");
        normalMap.put(9, "c");
        normalMap.put(11, "d");
        normalMap.put(10, "e");
        normalMap.put(25, "f");
        normalMap.put(27, "g");
        normalMap.put(26, "h");
        normalMap.put(17, "i");
        normalMap.put(19, "j");
        normalMap.put(40, "k");
        normalMap.put(56, "l");
        normalMap.put(41, "m");
        normalMap.put(43, "n");
        normalMap.put(42, "o");
        normalMap.put(57, "p");
        normalMap.put(59, "q");
        normalMap.put(58, "r");
        normalMap.put(49, "s");
        normalMap.put(51, "t");
        normalMap.put(44, "u");
        normalMap.put(60, "v");
        normalMap.put(45, "x");
        normalMap.put(47, "y");
        normalMap.put(46, "z");
        normalMap.put(23, "w");
        normalMap.put(12, "ch");
        normalMap.put(13, "sh");
        normalMap.put(15, "th");
        normalMap.put(16, ",");
        normalMap.put(48, ";");
        normalMap.put(32, "'");
        normalMap.put(18, ":");
        normalMap.put(36, "-");
        normalMap.put(5, ".");
        normalMap.put(22, ".");
        normalMap.put(50, "!");
        normalMap.put(52, "?");
        normalMap.put(33, "\\");

        capitalMap = new HashMap<Integer, String>();
        capitalMap.put(8, "A");
        capitalMap.put(24, "B");
        capitalMap.put(9, "C");
        capitalMap.put(11, "D");
        capitalMap.put(10, "E");
        capitalMap.put(25, "F");
        capitalMap.put(27, "G");
        capitalMap.put(26, "H");
        capitalMap.put(17, "I");
        capitalMap.put(19, "J");
        capitalMap.put(40, "K");
        capitalMap.put(56, "L");
        capitalMap.put(41, "M");
        capitalMap.put(43, "N");
        capitalMap.put(42, "O");
        capitalMap.put(57, "P");
        capitalMap.put(59, "Q");
        capitalMap.put(58, "R");
        capitalMap.put(49, "S");
        capitalMap.put(51, "T");
        capitalMap.put(44, "U");
        capitalMap.put(60, "V");
        capitalMap.put(45, "W");
        capitalMap.put(47, "X");
        capitalMap.put(46, "Y");
        capitalMap.put(23, "Z");
        capitalMap.put(12, "CH");
        capitalMap.put(13, "SH");
        capitalMap.put(15, "TH");
        capitalMap.put(16, ",");
        capitalMap.put(48, ";");
        capitalMap.put(32, "'");
        capitalMap.put(18, ":");
        capitalMap.put(36, "-");
        capitalMap.put(5, ".");
        capitalMap.put(22, ".");
        capitalMap.put(50, "!");
        capitalMap.put(52, "?");
        capitalMap.put(33, "\\");

        numberMap = new HashMap<Integer, String>();
        numberMap.put(8, "1");
        numberMap.put(24, "2");
        numberMap.put(9, "3");
        numberMap.put(11, "4");
        numberMap.put(10, "5");
        numberMap.put(25, "6");
        numberMap.put(27, "7");
        numberMap.put(26, "8");
        numberMap.put(17, "9");
        numberMap.put(19, "0");
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
