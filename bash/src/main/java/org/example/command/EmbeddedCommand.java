package org.example.command;

import org.example.command.embedded.CatCommand;
import org.example.command.embedded.EchoCommand;
import org.example.command.embedded.EmptyCommand;
import org.example.command.embedded.ExitCommand;
import org.example.command.embedded.GrepCommand;
import org.example.command.embedded.PwdCommand;
import org.example.command.embedded.WcCommand;

import java.util.List;
import java.util.Map;

/**
 * Command with implementation inside interpreter
 */
public class EmbeddedCommand extends Command {
    private final String commandName;

    public EmbeddedCommand(String commandName, List<String> commandLineArguments,
            List<EnvironmentVariable> environmentVariables) {
        super(null, commandLineArguments, environmentVariables);
        this.commandName = commandName;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public boolean isEmbeddedCommand() {
        return true;
    }

    public static final Map<String, Class<? extends EmbeddedCommand>> commands = Map.ofEntries(
            Map.entry("echo", EchoCommand.class),
            Map.entry("pwd", PwdCommand.class),
            Map.entry("wc", WcCommand.class),
            Map.entry("cat", CatCommand.class),
            Map.entry("exit", ExitCommand.class),
            Map.entry("grep", GrepCommand.class),
            Map.entry("", EmptyCommand.class));

    public static boolean isEmbeddedCommandName(String commandName) {
        return commands.containsKey(commandName);
    }

    public static EmbeddedCommand createEmbeddedCommand(String commandName, List<String> commandLineArguments,
            List<EnvironmentVariable> environmentVariables) {
        switch (commandName) {
            case "echo" -> {
                return new EchoCommand(commandLineArguments, environmentVariables);
            }
            case "pwd" -> {
                return new PwdCommand(commandLineArguments, environmentVariables);
            }
            case "wc" -> {
                return new WcCommand(commandLineArguments, environmentVariables);
            }
            case "cat" -> {
                return new CatCommand(commandLineArguments, environmentVariables);
            }
            case "exit" -> {
                return new ExitCommand(commandLineArguments, environmentVariables);
            }
            case "grep" -> {
                return new GrepCommand(commandLineArguments, environmentVariables);
            }
            case "" -> {
                return new EmptyCommand(commandLineArguments, environmentVariables);
            }
        }
        throw new IllegalArgumentException("Unknown embedded command name");
    }

}
