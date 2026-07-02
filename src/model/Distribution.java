package model;

import java.util.HashMap;
import java.util.Map;

public class Distribution {

    private final Map<Integer, Double> probabilities = new HashMap<>();

    public void addProbability(int value, double probability) {
        probabilities.merge(value, probability, Double::sum);
    }

    public Map<Integer, Double> getProbabilities() {
        return probabilities;
    }
}