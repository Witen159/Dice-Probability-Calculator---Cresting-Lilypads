package model.Nodes;

public class NumberNode implements ExpressionNode {

    private final int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
