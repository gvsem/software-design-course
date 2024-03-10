package org.example.execution.descriptor;

import org.example.execution.exception.ExecutionException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Descriptor implemented as file
 */
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

    @Override
    public void print(String x) throws IOException {
        if (this.getType().equals(Type.InputOutput) || this.getType().equals(Type.Output)) {
            try (FileWriter fw = new FileWriter(file, true)) {
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(x);
                bw.flush();
            }
        } else if (this.getType().equals(Type.Input)) {
            throw new UnsupportedOperationException("Writing to input descriptor is prohibited");
        } else {
            throw new UnsupportedOperationException("Unknown descriptor type");
        }
    }
}
