package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.util.List;

/**
 * Implementation of reserved empty embedded command
 */
public class EmptyCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        for (EnvironmentVariable var : getEnvironmentVariables()) {
            context.getEnvironment().put(var.variableName(), var.value());
        }
        return 0;
    }

    public EmptyCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("", commandLineArguments, environmentVariables);
    }


}
