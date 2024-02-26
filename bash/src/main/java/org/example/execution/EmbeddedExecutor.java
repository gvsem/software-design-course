package org.example.execution;

import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.command.embedded.CatCommand;
import org.example.command.embedded.EchoCommand;
import org.example.command.embedded.PwdCommand;
import org.example.command.embedded.WcCommand;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IEmbeddedExecutor;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.util.Map;

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
