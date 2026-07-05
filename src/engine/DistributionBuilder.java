package engine;

import model.Distribution;

public class DistributionBuilder {

    public static Distribution buildDiceDistribution(int dice, int sides) {

        Distribution distribution = new Distribution();

        distribution.addProbability(0, 1.0);

        for (int i = 0; i < dice; i++) {

            Distribution next = new Distribution();

            for (var entry : distribution.getProbabilities().entrySet()) {

                int currentSum = entry.getKey();
                double currentProbability = entry.getValue();

                for (int roll = 1; roll <= sides; roll++) {

                    next.addProbability(
                            currentSum + roll,
                            currentProbability / sides
                    );

                }
            }

            distribution = next;
        }

        return distribution;
    }
}