package org.example.ast.concrete;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.token.AbstractToken;
import org.example.command.Command;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;
import org.example.interfaces.ISubstitutor;
import org.example.parsing.exception.ParseException;

import java.io.IOException;
import java.util.List;

import lombok.Getter;

/**
 * Expression of resolved command which was acquired by {@link ISubstitutor} in {@link UnresolvedCommandExpression}
 */
public class ResolvedCommandExpression extends AbstractExpression {

    @Getter
    private final List<AbstractToken> tokens;

    public ResolvedCommandExpression(List<AbstractToken> tokens) {
        this.tokens = tokens;
    }

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        try {
            Command command = executor.getParser().parse(this);
            return command.run(executor, context);
        } catch (ParseException e) {
            throw new ExecutionException("bash : parse error", e);
        }
    }

}
