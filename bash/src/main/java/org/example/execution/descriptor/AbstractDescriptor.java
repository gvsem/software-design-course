package org.example.execution.descriptor;

import org.example.interfaces.IDescriptor;

import java.io.IOException;

/**
 * Base class for descriptors
 */
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

    public abstract void print(String x) throws IOException;

    public void println(String x) throws IOException {
        print(x + "\n");
    }

}
