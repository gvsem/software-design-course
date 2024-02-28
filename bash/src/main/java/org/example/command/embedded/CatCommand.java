package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        try {
            if (getCommandLineArguments().isEmpty()) {
                throw new ExecutionException("No mandatory argument <filename>");
            }

            Path path = Path.of(getCommandLineArguments().get(0));

            if (!path.isAbsolute()) {
                path = context.getWorkingDirectory().resolve(path);
            }

            if (!Files.exists(path)) {
                throw new ExecutionException("File does not exist: " + path);
            }

            String content = new String(Files.readAllBytes(path));
            if (!content.isEmpty() && content.charAt(content.length() - 1) == '\n')
                content = content.substring(0, content.length() - 1);
            context.getDescriptors().stdout.println(content);

        } catch (Exception e) {
            return 1;
        }

        return 0;
    }

    public CatCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("cat", commandLineArguments, environmentVariables);
    }
}