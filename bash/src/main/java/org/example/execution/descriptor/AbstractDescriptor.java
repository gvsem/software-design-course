package org.example.execution.descriptor;

import org.example.interfaces.IDescriptor;

public abstract class AbstractDescriptor implements IDescriptor {

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
