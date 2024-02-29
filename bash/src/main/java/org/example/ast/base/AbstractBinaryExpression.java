package org.example.ast.base;

import lombok.Getter;

/**
 * Base class for binary expressions
 */
public abstract class AbstractBinaryExpression extends AbstractExpression {

    @Getter
    protected final AbstractExpression left;

    @Getter
    protected final AbstractExpression right;

    public AbstractBinaryExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }
}
