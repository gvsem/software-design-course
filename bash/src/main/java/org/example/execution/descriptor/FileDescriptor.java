package org.example.execution.descriptor;

import org.example.execution.exception.ExecutionException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDescriptor extends AbstractDescriptor {
    private final File file;

    public FileDescriptor(Type type, File file) {
        super(type);
        this.file = file;
    }

    public File getFilename() {
        return file;
    }

    @Override
    public void redirect(SystemType type, ProcessBuilder processBuilder) throws ExecutionException {
        if (type.equals(SystemType.stdin)) {
            if (!file.exists()) {
                throw new ExecutionException("Input file does not exist: " + file.getName());
            }
            processBuilder.redirectInput(file);
        } else if (type.equals(SystemType.stdout)) {
            createOrClearFile(file);
            processBuilder.redirectOutput(file);
        } else if (type.equals(SystemType.stderr)) {
            createOrClearFile(file);
            processBuilder.redirectOutput(file);
        } else {
            throw new UnsupportedOperationException("Unknown descriptor system type");
        }
    }

    private static void createOrClearFile(File file) throws ExecutionException {
        try {
            new FileOutputStream(file).close();
        } catch (IOException e) {
            throw new ExecutionException("Failed to create output file: " + file.getName(), e);
        }
    }
}
