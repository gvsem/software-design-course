package org.example.execution.descriptor;

import java.nio.file.Path;

public class FileDescriptor extends AbstractDescriptor {
    private final Path filename;

    public FileDescriptor(Type type, Path filename) {
        super(type);
        this.filename = filename;
    }

    public Path getFilename() {
        return filename;
    }
}
