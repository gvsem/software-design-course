package org.example.execution.context;

import org.example.execution.descriptor.DescriptorSet;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Context {
    private final Environment environment = new Environment();
    private final Path workingDirectory = Paths.get("").toAbsolutePath();
    private final DescriptorSet descriptors = new DescriptorSet();

    public Environment getEnvironment() {
        return environment;
    }

    public Path getWorkingDirectory() {
        return workingDirectory;
    }

    public DescriptorSet getDescriptors() {
        return descriptors;
    }
}
