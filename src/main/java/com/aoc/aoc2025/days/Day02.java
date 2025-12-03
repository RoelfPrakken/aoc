package com.aoc.aoc2025.days;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import com.aoc.Day;
import com.aoc.aoc2025.util.Range;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day02 extends Day {

    public Day02() {
        super(2, 2025);
    }

    @Override
    public Object part1(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        AtomicLong sum = new AtomicLong(0);

        Arrays.stream(input.get(0).split(","))
                .map(r -> {
                    String[] parts = r.split("-");
                    return new Range(parts[0], parts[1]);
                })
                .forEach(r -> {
                    for (long i = r.getStart(); i <= r.getEnd(); i++) {
                        if (isDoubleRepeatedDigits(i)) {
                            sum.getAndAdd(i);
                        }
                    }
                });
        return sum.get();
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        AtomicLong sum = new AtomicLong(0);

        Arrays.stream(input.get(0).split(","))
                .map(r -> {
                    String[] parts = r.split("-");
                    return new Range(parts[0], parts[1]);
                })
                .forEach(r -> {
                    for (long i = r.getStart(); i <= r.getEnd(); i++) {
                        if (containsOnlySubstringsEfficient(i)) {
                            sum.getAndAdd(i);
                        }
                    }
                });
        return sum.get();
    }

    public static void main(String[] args) {
        Day02 day = new Day02();
        day.runExample();
        day.run();
    }

    public boolean isDoubleRepeatedDigits(long n) {
        String s = Long.toString(Math.abs(n)); // evt. minteken negeren

        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }

        String first = s.substring(0, len / 2);
        String second = s.substring(len / 2);

        return first.equals(second);
    }

    public boolean isMadeOfRepeatingUnits(long n) {
        String s = Long.toString(Math.abs(n)); // negeer eventueel '-'
        int len = s.length();
        if (len <= 1) {
            return false; // één cijfer kan niet repeteren
        }

        // probeer alle mogelijke lengtes van het repeterende gedeelte
        for (int unitLen = 1; unitLen <= len / 2; unitLen++) {
            if (len % unitLen != 0) {
                continue; // lengte moet deelbaar door 2 zijn
            }
            String unit = s.substring(0, unitLen);
            boolean ok = true;

            // check of elke volgende deel gelijk is aan het repeterende gedeelte
            for (int i = unitLen; i < len; i += unitLen) {
                if (!s.regionMatches(i, unit, 0, unitLen)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true; // hele string = herhaling van dit gedeelte
            }
        }

        return false;
    }

    public boolean containsOnlySubstringsEfficient(long i) { // picked from Baeldung
        String string = Long.toString(Math.abs(i));
        return ((string + string).indexOf(string, 1) != string.length());
    }

}
