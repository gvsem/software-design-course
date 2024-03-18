package org.example.interfaces;

import org.example.ast.concrete.ResolvedCommandExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.execution.context.Context;

public interface ISubstitutor {
    ResolvedCommandExpression resolve(UnresolvedCommandExpression expression, Context context);
}
