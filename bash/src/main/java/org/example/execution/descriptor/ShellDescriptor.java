package org.example.execution.descriptor;

public class ShellDescriptor extends AbstractDescriptor {

    private ShellDescriptor(Type type) {
        super(type);
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

    private static final ShellDescriptor stdin = new ShellDescriptor(Type.Input);
    private static final ShellDescriptor stdout = new ShellDescriptor(Type.Output);
    private static final ShellDescriptor stderr = new ShellDescriptor(Type.Output);

    public static ShellDescriptor stdin() {
        return stdin;
    }

    public static ShellDescriptor stdout() {
        return stdout;
    }

    public static ShellDescriptor stderr() {
        return stderr;
    }

}
