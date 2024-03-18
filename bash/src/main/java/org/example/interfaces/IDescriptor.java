package org.example.interfaces;

import org.example.execution.exception.ExecutionException;

public interface IDescriptor {

    enum SystemType {
        stdin,
        stdout,
        stderr
    }

    void redirect(SystemType type, ProcessBuilder processBuilder) throws ExecutionException;
}
