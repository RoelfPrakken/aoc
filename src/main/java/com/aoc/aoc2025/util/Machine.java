package com.aoc.aoc2025.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Machine {

    private String finalState; // the part between []
    private List<Button> buttons; // the parts (List of Integers) between ()
    private List<Integer> joltageRequirements; // the part (List of Integers) between {}
    private String startStateLights;

    public Machine(String line) {
        // Parse final state: [...]
        Matcher stateMatcher = Pattern.compile("\\[(.*?)\\]").matcher(line);
        if (stateMatcher.find()) {
            this.finalState = stateMatcher.group(1);
            this.startStateLights = ".".repeat(finalState.length());
        }

        // Parse buttons: (...)
        this.buttons = new ArrayList<>();
        Matcher buttonMatcher = Pattern.compile("\\((.*?)\\)").matcher(line);
        while (buttonMatcher.find()) {
            String content = buttonMatcher.group(1);
            List<Integer> lights = Arrays.stream(content.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            this.buttons.add(new Button(lights));
        }

        // Parse joltage requirements: {...}
        Matcher requirementsMatcher = Pattern.compile("\\{(.*?)\\}").matcher(line);
        if (requirementsMatcher.find()) {
            String content = requirementsMatcher.group(1);
            this.joltageRequirements = Arrays.stream(content.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
    }

    public int solveLights() {
        Queue<LightsState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new LightsState(startStateLights, 0));
        visited.add(startStateLights);

        while (!queue.isEmpty()) {
            LightsState current = queue.poll();
            if (current.state.equals(finalState)) {
                return current.depth;
            }

            for (Button button : buttons) {
                String nextState = applyButtonToLights(current.state, button);
                if (visited.add(nextState)) {
                    queue.add(new LightsState(nextState, current.depth + 1));
                }
            }
        }
        return -1; // Unreachable
    }

    private String applyButtonToLights(String currentState, Button button) {
        StringBuilder sb = new StringBuilder(currentState);
        for (Integer light : button.getLightsToToggle()) {
            char c = sb.charAt(light);
            sb.setCharAt(light, c == '.' ? '#' : '.');
        }
        return sb.toString();
    }

    private record LightsState(String state, int depth) {
    }
}
