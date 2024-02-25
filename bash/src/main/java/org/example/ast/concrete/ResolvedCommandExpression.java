package org.example.ast.concrete;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.util.List;

import lombok.Getter;

public class ResolvedCommandExpression extends AbstractExpression {

    @Getter
    private final List<AbstractToken> tokens;

    public ResolvedCommandExpression(List<AbstractToken> tokens) {
        this.tokens = tokens;
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new UnsupportedOperationException();
    }

}
