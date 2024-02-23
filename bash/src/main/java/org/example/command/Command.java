package org.example.command;

import org.example.annotations.Nullable;
import org.example.interfaces.Executable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Command implements Executable {
    private final @Nullable Path executable;
    private final List<String> commandLineArguments;
    private final List<EnvironmentVariable> environmentVariables;

    public Command(@Nullable Path executable, List<String> commandLineArguments, List<EnvironmentVariable> environmentVariables) {
        this.executable = executable;
        this.commandLineArguments = commandLineArguments;
        this.environmentVariables = environmentVariables;
    }

    public Optional<Path> getExecutable() {
        return Optional.ofNullable(executable);
    }

    public List<String> getCommandLineArguments() {
        return commandLineArguments;
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return environmentVariables;
    }

    @Override
    public int run(IExecutor executor, Context context) throws ExecutionException {
        return executor.executeProcess(this, context);
    }

    public boolean isEmbeddedCommand() {
        return false;
    }
}
