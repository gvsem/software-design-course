package org.example.interfaces;

import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;

public interface Executable {
    int run(IExecutor executor, Context context) throws ExecutionException;
}
