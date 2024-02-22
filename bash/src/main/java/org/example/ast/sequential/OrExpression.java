package org.example.ast.sequential;

import org.example.ast.base.AbstractBinaryExpression;
import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

public class OrExpression extends AbstractBinaryExpression {
    public OrExpression(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new UnsupportedOperationException();
    }
}
