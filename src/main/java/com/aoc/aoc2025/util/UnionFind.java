package com.aoc.aoc2025.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind<T> {

    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> size = new HashMap<>();
    private int numberOfSets = 0;

    public UnionFind(List<T> elements) {
        for (T element : elements) {
            parent.put(element, element);
            size.put(element, 1);
        }
        numberOfSets = elements.size();
    }

    public T find(T element) {
        if (element.equals(parent.get(element))) {
            return element;
        }
        T root = find(parent.get(element));
        parent.put(element, root);
        return root;
    }

    public boolean union(T element1, T element2) {
        T root1 = find(element1);
        T root2 = find(element2);

        if (root1.equals(root2)) {
            return false;
        }

        if (size.get(root1) < size.get(root2)) {
            parent.put(root1, root2);
            size.put(root2, size.get(root1) + size.get(root2));
        } else {
            parent.put(root2, root1);
            size.put(root1, size.get(root1) + size.get(root2));
        }
        numberOfSets--;
        return true;
    }

    public int sets() {
        return numberOfSets;
    }

    public List<Integer> getComponentSizes() {
        List<Integer> sizes = new ArrayList<>();
        for (T element : parent.keySet()) {
            if (element.equals(parent.get(element))) {
                sizes.add(size.get(element));
            }
        }
        return sizes;
    }
}
