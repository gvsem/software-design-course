package org.example.command;

import org.example.command.Command;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.Executable;
import org.example.interfaces.IExecutor;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class EmbeddedCommand extends Command {
    private final String commandName;

    public EmbeddedCommand(String commandName, List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        super(null, commandLineArguments, environmentVariables);
        this.commandName = commandName;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public boolean isEmbeddedCommand() {
        return true;
    }
}
