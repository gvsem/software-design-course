package org.example.execution.descriptor;

public class ShellDescriptor extends AbstractDescriptor {

    private ShellDescriptor(Type type) {
        super(type);
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
