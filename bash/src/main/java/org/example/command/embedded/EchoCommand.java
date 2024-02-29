package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of embedded command 'echo'
 */
public class EchoCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        try {
            int size = getCommandLineArguments().size();
            for (int i=0; i<size-1; i++) {
                context.getDescriptors().stdout.print( getCommandLineArguments().get(i) + " ");
            }
            context.getDescriptors().stdout.print( getCommandLineArguments().get(size-1) + "\n");
        } catch (IOException e) {
            return 1;
        }
        return 0;
    }

    public EchoCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("echo", commandLineArguments, environmentVariables);
    }
}

