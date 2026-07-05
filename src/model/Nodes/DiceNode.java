package model.Nodes;

public class DiceNode implements ExpressionNode {

    private final int dice;
    private final int sides;

    public DiceNode(int dice, int sides) {
        this.dice = dice;
        this.sides = sides;
    }

    public int getDice() {
        return dice;
    }

    public int getSides() {
        return sides;
    }
}
