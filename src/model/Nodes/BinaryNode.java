package model.Nodes;

public class BinaryNode implements ExpressionNode {

    private final ExpressionNode left;
    private final ExpressionNode right;
    private final Operator operator;

    public BinaryNode(ExpressionNode left,
                      Operator operator,
                      ExpressionNode right) {

        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }
}
