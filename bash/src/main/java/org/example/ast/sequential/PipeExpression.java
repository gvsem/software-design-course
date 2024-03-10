package org.example.ast.sequential;

import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.IOException;

import org.example.ast.base.AbstractBinaryExpression;
import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

/**
 * AST node corresponding to | operator in bash
 */

public class PipeExpression extends AbstractBinaryExpression {
    public PipeExpression(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        // currently, pipe may only contain UnresolvedCommandExpression
        return executor.getSubstitutor()
                .resolve((UnresolvedCommandExpression) this.left, context)
                .run(executor, context);
    }

    // testing purpose
    public String toString() {
        return left.toString() + " | " + right.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PipeExpression))
            return false;
        PipeExpression that = (PipeExpression) o;
        return this.toString().equals(that.toString());
    }
}
