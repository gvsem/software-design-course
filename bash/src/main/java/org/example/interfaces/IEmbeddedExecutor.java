package org.example.interfaces;

import org.example.command.Command;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;

import java.io.IOException;

public interface IEmbeddedExecutor {
    int executeProcess(Command command, Context context) throws ExecutionException, IOException;
}
