package org.example.ast.concrete;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;

/**
 * Leaf of AST corresponding to a command text with, possibly, substitutions
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UnresolvedCommandExpression extends AbstractExpression {

    protected final String command;

    public UnresolvedCommandExpression(String command) {
        this.command = command;
    }

    public ResolvedCommandExpression resolve(IExecutor executor, Context context) {
        return executor.getSubstitutor().resolve(this, executor, context);
    }

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        return resolve(executor, context).run(executor, context);
    }
}
