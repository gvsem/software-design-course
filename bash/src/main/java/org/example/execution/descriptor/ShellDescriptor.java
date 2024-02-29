package org.example.execution.descriptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Descriptor implemented as file
 */
public class ShellDescriptor extends AbstractDescriptor {

    private final SystemType systemType;

    private ShellDescriptor(SystemType systemType, Type type) {
        super(type);
        this.systemType = systemType;
    }

    @Override
    public void redirect(SystemType type, ProcessBuilder processBuilder) {
        if (type.equals(SystemType.stdin)) {
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        } else if (type.equals(SystemType.stdout)) {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        } else if (type.equals(SystemType.stderr)) {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        } else {
            throw new UnsupportedOperationException("Unknown descriptor system type");
        }
    }

    private static final ShellDescriptor stdin = new ShellDescriptor(SystemType.stdin, Type.Input);
    private static final ShellDescriptor stdout = new ShellDescriptor(SystemType.stdout, Type.Output);
    private static final ShellDescriptor stderr = new ShellDescriptor(SystemType.stderr, Type.Output);

    public static ShellDescriptor stdin() {
        return stdin;
    }

    public static ShellDescriptor stdout() {
        return stdout;
    }

    public static ShellDescriptor stderr() {
        return stderr;
    }

    @Override
    public void print(String x) throws IOException {
        if (this.systemType.equals(SystemType.stdin)) {
            throw new UnsupportedOperationException("Writing to input descriptor is prohibited");
        } else if (this.systemType.equals(SystemType.stdout)) {
            System.out.print(x);
        } else if (this.systemType.equals(SystemType.stderr)) {
            System.err.print(x);
        } else {
            throw new UnsupportedOperationException("Unknown descriptor type");
        }
    }

}
