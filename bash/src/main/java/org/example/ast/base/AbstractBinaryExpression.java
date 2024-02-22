package org.example.ast.base;

public abstract class AbstractBinaryExpression extends AbstractExpression {
    protected final AbstractExpression left;
    protected final AbstractExpression right;

    public AbstractBinaryExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }
}
