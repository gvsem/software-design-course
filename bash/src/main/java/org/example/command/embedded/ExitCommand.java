package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.command.exception.ExitException;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.util.List;

/**
 * Implementation of embedded command 'wc'
 */
public class ExitCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        throw new ExitException();
    }

    public ExitCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("exit", commandLineArguments, environmentVariables);
    }
}
