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
import java.util.Arrays;
import java.util.List;

public class WcCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        try {
            String filePath = getCommandLineArguments().get(0);
            Path path = Path.of(filePath);

            if (!path.isAbsolute()) {
                path = context.getWorkingDirectory().resolve(path);
            }

            if (!Files.exists(path)) {
                throw new ExecutionException("File does not exist: " + path);
            }

            String content = new String(Files.readAllBytes(path));
            String[] lines = content.split("\r\n|\r|\n");
            String[] words = content.split("\\s+");
            int bytes = Files.readAllBytes(path).length;

            context.getDescriptors().stdout.print(lines.length + " " + words.length + " " + bytes);

        } catch (IOException e) {
            return 1;
        }

        return 0;
    }

    public WcCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("wc", commandLineArguments, environmentVariables);
    }
}

