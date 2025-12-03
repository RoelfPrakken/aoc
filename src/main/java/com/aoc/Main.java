package com.aoc;

import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the day to run (e.g., 1).");
            return;
        }

        int day = Integer.parseInt(args[0]);
        try {
            String className = String.format("com.aoc.aoc2025.days.Day%02d", day);
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            Day dayInstance = (Day) constructor.newInstance();
            dayInstance.run();
        } catch (ClassNotFoundException e) {
            System.err.println("Day " + day + " not implemented yet.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
