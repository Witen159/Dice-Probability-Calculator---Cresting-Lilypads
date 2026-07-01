package parser;

import model.Comparison;
import model.DiceExpression;
import model.DiceTerm;
import java.util.List;

public class ExpressionParser {

    private final List<Token> tokens;

    private int pos = 0;

    public ExpressionParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public DiceExpression parse() {

        DiceExpression expression = new DiceExpression();

        boolean negative = false;

        while (true) {

            Token token = current();

            if (isComparison(token)) {
                break;
            }

            if (token.getType() == TokenType.PLUS) {
                negative = false;
                next();
                continue;
            }

            if (token.getType() == TokenType.MINUS) {
                negative = true;
                next();
                continue;
            }

            if (token.getType() == TokenType.DICE) {

                String[] parts = token.getText().split("[dD]");

                int dice = Integer.parseInt(parts[0]);
                int sides = Integer.parseInt(parts[1]);

                expression.addTerm(
                        new DiceTerm(negative, dice, sides, true));

                negative = false;
                next();
                continue;
            }

            if (token.getType() == TokenType.NUMBER) {

                int value = Integer.parseInt(token.getText());

                expression.addTerm(
                        new DiceTerm(negative, value, 0, false));

                negative = false;
                next();
                continue;
            }

            throw new RuntimeException("Unexpected token: " + token);
        }

        Token comparison = current();

        switch (comparison.getType()) {

            case GREATER:
                expression.setComparison(Comparison.GREATER);
                break;

            case GREATER_EQUAL:
                expression.setComparison(Comparison.GREATER_EQUAL);
                break;

            case LESS:
                expression.setComparison(Comparison.LESS);
                break;

            case LESS_EQUAL:
                expression.setComparison(Comparison.LESS_EQUAL);
                break;

            case EQUAL:
                expression.setComparison(Comparison.EQUAL);
                break;

            case NOT_EQUAL:
                expression.setComparison(Comparison.NOT_EQUAL);
                break;

            default:
                throw new RuntimeException("Expected comparison.");
        }

        next();

        Token target = current();

        if (target.getType() != TokenType.NUMBER)
            throw new RuntimeException("Expected target number.");

        expression.setTarget(
                Integer.parseInt(target.getText()));

        return expression;
    }

    private Token current() {
        return tokens.get(pos);
    }

    private void next() {
        pos++;
    }

    private boolean isComparison(Token token) {

        switch (token.getType()) {

            case GREATER:
            case GREATER_EQUAL:
            case LESS:
            case LESS_EQUAL:
            case EQUAL:
            case NOT_EQUAL:
                return true;

            default:
                return false;
        }
    }
}