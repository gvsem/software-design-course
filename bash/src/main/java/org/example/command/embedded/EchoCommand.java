package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.util.List;

public class EchoCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        try {
            for (String argument : getCommandLineArguments()) {
                context.getDescriptors().stdout.print(argument + " ");
            }
            context.getDescriptors().stdout.println("");
        } catch (IOException e) {
            return 1;
        }
        return 0;
    }

    public EchoCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("echo", commandLineArguments, environmentVariables);
    }
}

