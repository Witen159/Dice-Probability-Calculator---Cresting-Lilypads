package engine;

import model.Distribution;

public class DistributionMath {

    public static Distribution add(Distribution a, Distribution b) {

        Distribution result = new Distribution();

        for (var left : a.getProbabilities().entrySet()) {

            for (var right : b.getProbabilities().entrySet()) {

                result.addProbability(

                        left.getKey() + right.getKey(),

                        left.getValue() * right.getValue());

            }
        }

        return result;
    }

    public static Distribution subtract(Distribution a,
                                        Distribution b) {

        Distribution result = new Distribution();

        for (var left : a.getProbabilities().entrySet()) {

            for (var right : b.getProbabilities().entrySet()) {

                result.addProbability(

                        left.getKey() - right.getKey(),

                        left.getValue() * right.getValue());

            }
        }

        return result;
    }

    public static Distribution addConstant(Distribution d,
                                           int constant) {

        Distribution result = new Distribution();

        for (var entry : d.getProbabilities().entrySet()) {

            result.addProbability(

                    entry.getKey() + constant,

                    entry.getValue());

        }

        return result;
    }
}