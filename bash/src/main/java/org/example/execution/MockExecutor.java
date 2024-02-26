package org.example.execution;

import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IEmbeddedExecutor;
import org.example.interfaces.IExecutor;
import org.example.interfaces.IParser;
import org.example.interfaces.ISubstitutor;

import java.io.IOException;

public class MockExecutor implements IExecutor {

    @Override
    public int executeProcess(Command command, Context context) throws ExecutionException {
        throw new ExecutionException("MockExecutor can not run execute process");
    }

    @Override
    public ISubstitutor getSubstitutor() {
        throw new RuntimeException("MockExecutor has no substitutor");
    }

    @Override
    public IParser getParser() {
        throw new RuntimeException("MockExecutor has no parser");
    }
}
