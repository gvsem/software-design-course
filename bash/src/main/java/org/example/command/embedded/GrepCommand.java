package org.example.command.embedded;

import org.apache.commons.cli.*;
import org.example.command.EmbeddedCommand;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of embedded command 'grep'
 */
public class GrepCommand extends EmbeddedCommand {
    private final Options options = new Options();

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, getCommandLineArguments().toArray(new String[0]));
        } catch (ParseException e) {
            throw new ExecutionException("Error parsing command line arguments", e);
        }

        boolean wholeWordMatch = cmd.hasOption("w");
        boolean caseInsensitiveMatch = cmd.hasOption("i");
        int linesAfterMatch = cmd.hasOption("A") ? Integer.parseInt(cmd.getOptionValue("A")) : 0;

        Path filePath = Paths.get(cmd.getArgList().get(cmd.getArgList().size() - 1));
        String regex = cmd.getArgList().get(cmd.getArgList().size() - 2);

        if (wholeWordMatch) {
            regex = "(\\s|^)" + regex + "(\\s|$)";
        }

        if (caseInsensitiveMatch) {
            regex = regex.toLowerCase();
        }

        regex = ".*(" + regex + ").*";

        int tochnoPrint = 0;
        Boolean wasPrint = false;


        String content = new String(Files.readAllBytes(filePath));

        String proxi;

        for (String line : content.split("\n")) {
            wasPrint = false;
            proxi = line;
            if (caseInsensitiveMatch) {
                proxi = proxi.toLowerCase();
            }
            if (tochnoPrint > 0) {
                context.getDescriptors().stdout.println(line);
                wasPrint = true;
                tochnoPrint--;

            }
            if (proxi.matches(regex)) {
                tochnoPrint = linesAfterMatch;
                if (!wasPrint) {
                    context.getDescriptors().stdout.println(line);
                }
            }
        }
        return 0;
    }

    public GrepCommand(List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super("grep", commandLineArguments, environmentVariables);
        options.addOption("w", false, "whole word match");
        options.addOption("i", false, "case insensitive match");
        options.addOption("A", true, "print specified number of lines after match");
    }
}