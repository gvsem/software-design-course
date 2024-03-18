package org.example.execution;

import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IEmbeddedExecutor;

import java.io.IOException;

public class EmbeddedExecutor implements IEmbeddedExecutor {

    @Override
    public int executeProcess(Command command, Context context) throws ExecutionException {
        if (!command.isEmbeddedCommand() || !(command instanceof EmbeddedCommand embeddedCommand)) {
            throw new ExecutionException("Embedded command executor can not run process");
        }

        try {
            return command.run(new MockExecutor(), context);
        } catch (IOException e) {
            throw new ExecutionException("bash : broken descriptor", e);
        }
    }
}
