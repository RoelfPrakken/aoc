package com.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    public static List<String> readInput(int day, int year) {
        return readResource(String.format("aoc%d/inputs/day%02d.txt", year, day));
    }

    public static List<String> readExample(int day, int year) {
        return readResource(String.format("aoc%d/examples/day%02d.txt", year, day));
    }

    public static List<String> readResource(String path) {
        try (InputStream inputStream = InputReader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + path);
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + path, e);
        }
    }
}
