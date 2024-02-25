package org.example.ast.concrete;

import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import lombok.Getter;

public class UnresolvedCommandExpression extends AbstractExpression {

    @Getter
    protected final String command;

    public UnresolvedCommandExpression(String command) {
        this.command = command;
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new UnsupportedOperationException();
    }
}
