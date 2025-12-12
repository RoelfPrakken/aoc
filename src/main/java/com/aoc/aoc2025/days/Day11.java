package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.aoc.Day;
import com.aoc.aoc2025.util.RouteCounter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day11 extends Day {

    public Day11() {
        super(11, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return true;
    }

    @Override
    public Object part1(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        Map<String, Device> devices = parseGraph(input);

        Device start = devices.get("you");
        Device end = devices.get("out");

        if (start == null || end == null) {
            return 0L;
        }

        return new RouteCounter<Device>(Device::getConnections).countRoutes(start, end);
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        Map<String, Device> devices = parseGraph(input);

        Device svr = devices.get("svr");
        Device out = devices.get("out");
        Device dac = devices.get("dac");
        Device fft = devices.get("fft");

        if (svr == null || out == null || dac == null || fft == null) {
            return 0L;
        }

        RouteCounter<Device> counter = new RouteCounter<>(Device::getConnections);

        // path: svr -> dac -> fft -> out
        long dacFirst = counter.countRoutes(svr, dac) *
                counter.countRoutes(dac, fft) *
                counter.countRoutes(fft, out);

        // path: svr -> fft -> dac -> out
        long fftFirst = counter.countRoutes(svr, fft) *
                counter.countRoutes(fft, dac) *
                counter.countRoutes(dac, out);

        return dacFirst + fftFirst;
    }

    private Map<String, Device> parseGraph(List<String> input) {
        Map<String, Device> devices = new HashMap<>();

        // first pass: create all devices
        for (String line : input) {
            String name = line.split(":")[0].trim();
            devices.put(name, new Device());
        }

        // ensure "out" exists
        if (!devices.containsKey("out")) {
            devices.put("out", new Device());
        }

        // second pass: create connections
        for (String line : input) {
            String[] parts = line.split(":");
            String sourceName = parts[0].trim();
            Device source = devices.get(sourceName);

            if (parts.length > 1) {
                String[] targets = parts[1].trim().split("\\s+");
                for (String targetName : targets) {
                    if (targetName.isEmpty())
                        continue;
                    devices.putIfAbsent(targetName, new Device());
                    Device target = devices.get(targetName);
                    source.connect(target);
                }
            }
        }
        return devices;
    }

    public void runExamplePart2(String filename) {
        List<String> part2Example = com.aoc.InputReader.readResource(
                String.format("aoc%d/examples/%s", year, filename));
        System.out.println("--- Day " + dayNumber + " (Example Part 2) ---");
        long start = System.nanoTime();
        Object result = part2(part2Example);
        long time = System.nanoTime();
        System.out.println("Part 2: " + result + " (" + (time - start) / 1_000_000.0 + " ms)");
        System.out.println();
    }

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.runExample();
        day.runExamplePart2("day11_part2.txt");
        day.run();
    }

    public static class Device {

        private final List<Device> connections;

        public Device() {
            this.connections = new ArrayList<>();
        }

        public void connect(Device device) {
            this.connections.add(device);
        }

        public List<Device> getConnections() {
            return connections;
        }
    }
}
