package org.example.execution.descriptor;

public abstract class AbstractDescriptor {

    protected AbstractDescriptor(Type type) {
        this.type = type;
    }

    public enum Type {
        Input,
        Output,
        InputOutput
    }

    private final Type type;

    public final Type getType() {
        return this.type;
    }

}
