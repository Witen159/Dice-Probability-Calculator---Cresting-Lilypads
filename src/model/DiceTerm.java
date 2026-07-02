package model;

public class DiceTerm {
    private final boolean negative;
    private final boolean dice;

    private final int number;
    private final int sides;

    public DiceTerm(boolean negative, int number, int sides, boolean dice) {
        this.negative = negative;
        this.number = number;
        this.sides = sides;
        this.dice = dice;
    }

    public boolean isNegative() {
        return negative;
    }

    public boolean isDice() {
        return dice;
    }

    public int getNumber() {
        return number;
    }

    public int getSides() {
        return sides;
    }

    @Override
    public String toString() {
        String sign = negative ? "-" : "+";

        if (dice)
            return sign + number + "d" + sides;

        return sign + number;
    }
}
