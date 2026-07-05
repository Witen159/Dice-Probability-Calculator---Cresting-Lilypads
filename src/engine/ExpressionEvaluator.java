package engine;

import model.*;

public class ExpressionEvaluator {

    public static double evaluate(DiceExpression expression) {

        Distribution result = new Distribution();
        result.addProbability(0, 1.0);

        for (DiceTerm term : expression.getTerms()) {

            if (term.isDice()) {

                Distribution diceDistribution =
                        DistributionBuilder.buildDiceDistribution(
                                term.getNumber(),
                                term.getSides());

                if (term.isNegative()) {
                    result = DistributionMath.subtract(result,
                            diceDistribution);
                }
                else {
                    result = DistributionMath.add(result,
                            diceDistribution);
                }
            }
            else {

                if (term.isNegative()) {
                    result = DistributionMath.addConstant(
                            result,
                            -term.getNumber());
                }
                else {
                    result = DistributionMath.addConstant(
                            result,
                            term.getNumber());
                }
            }
        }

        return compare(
                result,
                expression.getComparison(),
                expression.getTarget());
    }

    private static double compare(
            Distribution distribution,
            Comparison comparison,
            int target) {

        double probability = 0;

        for (var entry : distribution.getProbabilities().entrySet()) {

            int value = entry.getKey();
            double p = entry.getValue();

            switch (comparison) {

                case GREATER:
                    if (value > target)
                        probability += p;
                    break;

                case GREATER_EQUAL:
                    if (value >= target)
                        probability += p;
                    break;

                case LESS:
                    if (value < target)
                        probability += p;
                    break;

                case LESS_EQUAL:
                    if (value <= target)
                        probability += p;
                    break;

                case EQUAL:
                    if (value == target)
                        probability += p;
                    break;

                case NOT_EQUAL:
                    if (value != target)
                        probability += p;
                    break;
            }
        }

        return probability;
    }
}