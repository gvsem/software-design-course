package org.example.ast.concrete;

import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

public class AssignExpression extends AbstractExpression {

    private final String variableName;
    private final AbstractExpression valueExpression;

    public AssignExpression(String variableName, AbstractExpression valueExpression) {
        this.variableName = variableName;
        this.valueExpression = valueExpression;
    }

    public String getVariableName() {
        return variableName;
    }

    public AbstractExpression getValueExpression() {
        return valueExpression;
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new UnsupportedOperationException();
    }
}
