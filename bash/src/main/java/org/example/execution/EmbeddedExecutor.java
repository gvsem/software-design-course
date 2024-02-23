package org.example.execution;

import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

public class EmbeddedExecutor implements IExecutor {

    @Override
    public int executeProcess(Command command, Context context) throws ExecutionException {
        if (!command.isEmbeddedCommand() || !(command instanceof EmbeddedCommand embeddedCommand)) {
            throw new ExecutionException("Embedded command executor can not run process");
        }

        System.out.println("Can not run " + embeddedCommand.getCommandName());
        return 1;
    }
}
