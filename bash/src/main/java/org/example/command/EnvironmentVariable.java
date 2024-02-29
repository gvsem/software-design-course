package org.example.command;

/**
 * Pair corresponding to environment variable name and its value
 */
public record EnvironmentVariable(String variableName, String value) {
}
