package com.aoc.aoc2025.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Button {

    private List<Integer> lightsToToggle;
}
