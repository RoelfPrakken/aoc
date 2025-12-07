package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.List;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day06 extends Day {

    public Day06() {
        super(6, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return true;
    }

    private List<String[]> parseInput(List<String> input) {
        List<String> rawLines = new ArrayList<>();
        int maxLen = 0;
        for (String line : input) {
            rawLines.add(line);
            maxLen = Math.max(maxLen, line.length());
        }

        List<Integer> separatorIndices = new ArrayList<>();
        for (int i = 0; i < maxLen; i++) {
            boolean isSeparator = true;
            for (String line : rawLines) {
                if (i < line.length() && line.charAt(i) != ' ') {
                    isSeparator = false;
                    break;
                }
            }
            if (isSeparator) {
                separatorIndices.add(i);
            }
        }
        separatorIndices.add(maxLen);

        List<String[]> grid = new ArrayList<>();
        for (String line : rawLines) {
            List<String> row = new ArrayList<>();
            int start = 0;
            for (int sep : separatorIndices) {
                if (sep > start) {
                    int colWidth = sep - start;
                    String token = "";
                    if (start < line.length()) {
                        int end = Math.min(sep, line.length());
                        token = line.substring(start, end);
                    }
                    // Pad token to colWidth
                    while (token.length() < colWidth) {
                        token += " ";
                    }
                    row.add(token);
                }
                start = sep + 1;
            }
            grid.add(row.toArray(new String[0]));
        }
        return grid;
    }

    @Override
    public Object part1(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        List<String[]> lines = parseInput(input);
        int columns = lines.get(0).length;
        long grandTotal = 0;
        int linecount = lines.size();

        for (int i = 0; i < columns; i++) {
            long total = 0;
            String operator = lines.get(linecount - 1)[i].trim();

            if (operator.equals("+")) {
                total = 0;
                for (int j = 0; j < linecount - 1; j++) {
                    String val = lines.get(j)[i].trim();
                    if (!val.isEmpty()) {
                        total += Long.parseLong(val);
                    }
                }
            } else if (operator.equals("*")) {
                total = 1;
                for (int j = 0; j < linecount - 1; j++) {
                    String val = lines.get(j)[i].trim();
                    if (!val.isEmpty()) {
                        total *= Long.parseLong(val);
                    }
                }
            }
            grandTotal += total;
        }

        return grandTotal;
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        List<String[]> lines = parseInput(input);
        int columns = lines.get(0).length;
        long grandTotal = 0;
        int linecount = lines.size();

        for (int i = columns - 1; i >= 0; i--) {
            long total = 0;
            String operator = lines.get(linecount - 1)[i].trim();

            if (operator.equals("+")) {
                total = 0;
            } else if (operator.equals("*")) {
                total = 1;
            }

            // Process vertical strips in this column block, from Right to Left
            int width = lines.get(0)[i].length();
            for (int k = width - 1; k >= 0; k--) {
                StringBuilder sb = new StringBuilder();
                // Read digits Top to Bottom
                for (int j = 0; j < linecount - 1; j++) {
                    char c = lines.get(j)[i].charAt(k);
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    }
                }

                if (sb.length() > 0) {
                    long val = Long.parseLong(sb.toString());
                    if (operator.equals("+")) {
                        total += val;
                    } else if (operator.equals("*")) {
                        total *= val;
                    }
                }
            }
            grandTotal += total;
        }
        return grandTotal;
    }

    public static void main(String[] args) {
        Day06 day = new Day06();
        day.runExample();
        day.run();
    }
}
