package org.example.ast.concrete;

import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

public class ResolvedCommandExpression extends UnresolvedCommandExpression {

    public ResolvedCommandExpression(String command) {
        super(command);
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new UnsupportedOperationException();
    }

}
