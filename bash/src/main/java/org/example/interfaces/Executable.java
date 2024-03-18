package org.example.interfaces;

import org.example.execution.context.Context;
import org.example.execution.descriptor.TempDescriptor;
import org.example.execution.exception.ExecutionException;

import java.io.IOException;
import java.nio.file.Files;

public interface Executable {

    /**
     * Execute command which ancestor implements
     * @param executor executor which supports real and embedded commands
     * @param context context in which command should be executed
     * @return exit code of this command
     * @throws IOException if broken pipe occurred
     * @throws ExecutionException if executor failed to run this command
     */
    int run(IExecutor executor, Context context) throws IOException, ExecutionException;

    default String executeAsExpression(IExecutor executor, Context context) throws IOException, ExecutionException {
        TempDescriptor tempStdout = new TempDescriptor();
        int code = this.run(executor, context.forkOutput(tempStdout));
        if (code == 0) {
            return new String(Files.readAllBytes(tempStdout.getFilename().toPath())).trim();
        } else {
            return "";
        }
    }
}
