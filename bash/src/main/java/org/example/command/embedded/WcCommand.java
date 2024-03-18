package org.example.command.embedded;

import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Implementation of embedded command 'wc'
 */
public class WcCommand extends EmbeddedCommand {

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        try {
            if (getCommandLineArguments().isEmpty()) {
                throw new ExecutionException("No mandatory argument <filename>");
            }

            Path path = Path.of(getCommandLineArguments().get(0));
            Path path_copy = path;

            if (!path.isAbsolute()) {
                path = context.getWorkingDirectory().resolve(path);
            }

            if (!Files.exists(path)) {
                throw new ExecutionException("File does not exist: " + path);
            }

            String content = new String(Files.readAllBytes(path));
            if (content.isEmpty()) {
                context.getDescriptors().stdout.println("0 0 0 " + path_copy);
                return 0;
            }

            String[] words = content.split("\\s+");
            int bytes = content.getBytes().length;

            BufferedReader reader = new BufferedReader(new FileReader(path_copy.toString()));
            int lines_count = 0;
            while (reader.readLine() != null)
                lines_count++;
            reader.close();

            int words_count;
            if (words.length == 1 && words[0].equals("")) {
                words_count = 0;
            } else {
                words_count = words.length;
            }

            if (content.split("\\s+").length > 0 && content.split("\\s+")[0].equals("")) {
                if (words_count > 0) {
                    words_count--;
                }
            }

            context.getDescriptors().stdout.println((lines_count) + " " + words_count + " " + bytes + " " + path_copy);

        } catch (IOException e) {
            return 1;
        }

        return 0;
    }

    public WcCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("wc", commandLineArguments, environmentVariables);
    }
}
