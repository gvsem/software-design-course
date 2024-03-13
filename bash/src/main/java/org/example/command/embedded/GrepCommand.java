package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Implementation of embedded command 'cat'
 */
public class GrepCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        throw new UnsupportedOperationException();
        // TODO: использовать библиотеку Apache Commons CLI (такое требование в задании, она уже подключена)
    }

    public GrepCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("grep", commandLineArguments, environmentVariables);
    }
}