package org.example.execution;

import org.example.command.Command;
import org.example.execution.context.Context;
import org.example.interfaces.IExecutor;

public class Executor implements IExecutor {

    public Executor() {

    }

    @Override
    public int executeProcess(Command command, Context context) {
        throw new UnsupportedOperationException();
    }
}
