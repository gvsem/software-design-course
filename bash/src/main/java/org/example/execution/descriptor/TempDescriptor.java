package org.example.execution.descriptor;

import java.io.File;
import java.io.IOException;

/**
 * Descriptor implemented as temporary file
 */
public class TempDescriptor extends FileDescriptor {

    public TempDescriptor() throws IOException {
        super(Type.InputOutput, File.createTempFile("bashik", "temp"));
    }

}
