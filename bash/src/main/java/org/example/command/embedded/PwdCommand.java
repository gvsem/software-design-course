package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of embedded command 'pwd'
 */
public class PwdCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        try {
            context.getDescriptors().stdout.println(context.getWorkingDirectory().toAbsolutePath().toString());
        } catch (IOException e) {
            return 1;
        }
        return 0;
    }

    public PwdCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("echo", commandLineArguments, environmentVariables);
    }
}

