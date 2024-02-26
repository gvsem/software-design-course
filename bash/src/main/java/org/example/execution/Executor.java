package org.example.execution;

import org.example.command.Command;
import org.example.command.EnvironmentVariable;
import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;
import org.example.interfaces.IParser;
import org.example.interfaces.ISubstitutor;
import org.example.parsing.Parser;
import org.example.substitution.Substitutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Executor implements IExecutor {

    private final EmbeddedExecutor embeddedExecutor = new EmbeddedExecutor();
    private final Substitutor substitutor = new Substitutor();
    private final Parser parser = new Parser();

    public Executor() {

    }

    @Override
    public ISubstitutor getSubstitutor() {
        return this.substitutor;
    }

    public IParser getParser() {
        return this.parser;
    }

    @Override
    public int executeProcess(Command command, Context context) throws ExecutionException {

        if (command.isEmbeddedCommand()) {
            return embeddedExecutor.executeProcess(command, context);
        }

        if (command.getExecutable().isEmpty()) {
            return 0;
        }

        Path executablePath = findExecutable(command.getExecutable().get(), context)
                .orElseThrow(() -> new ExecutionException("Executable not found : " + command.getExecutable().toString()));

        List<String> cmdLineArgs = new ArrayList<>();
        cmdLineArgs.add(executablePath.toString());
        cmdLineArgs.addAll(command.getCommandLineArguments());

        ProcessBuilder processBuilder = new ProcessBuilder(cmdLineArgs);
        for (EnvironmentVariable variable : command.getEnvironmentVariables()) {
            processBuilder.environment().put(variable.variableName(), variable.value());
        }

        File workingDirectory = context.getWorkingDirectory().toAbsolutePath().toFile();
        if (!workingDirectory.isDirectory() || !workingDirectory.exists()) {
            throw new ExecutionException("Working directory is not accessible: " + workingDirectory.toString());
        }
        processBuilder.directory(workingDirectory);

        context.getDescriptors().redirect(processBuilder);

        try {
            Process process = processBuilder.start();
            return process.waitFor();
        } catch (IOException e) {
            throw new ExecutionException("Executable not found", e);
        } catch (InterruptedException e) {
            throw new ExecutionException("Command execution was interrupted", e);
        }

    }

    private static Optional<Path> findExecutable(Path executablePath, Context context) {
        if (executablePath.isAbsolute() && isValidExecutablePath(executablePath)) {
            return Optional.of(executablePath);
        }

        for (String directoryName : context.getEnvironment().get("PATH").split(File.pathSeparator)) {
            Path resolvedPath = Path.of(directoryName).resolve(executablePath).toAbsolutePath();
            if (isValidExecutablePath(resolvedPath)) {
                return Optional.of(resolvedPath);
            }
        }

        return Optional.empty();
    }

    private static boolean isValidExecutablePath(Path executablePath) {
        File file = executablePath.toFile();
        return file.isFile() && file.canExecute();
    }

}
