package org.example.ast.concrete;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.ast.base.AbstractExpression;
import org.example.command.Command;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;
import org.example.parsing.exception.ParseException;

import java.io.IOException;

@EqualsAndHashCode(callSuper = false)
@Data
public class UnresolvedCommandExpression extends AbstractExpression {

    protected final String command;

    public UnresolvedCommandExpression(String command) {
        this.command = command;
    }

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        return executor.getSubstitutor().resolve(this, context).run(executor, context);
    }
}
