package org.example.execution.descriptor;

import org.example.execution.context.Context;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IDescriptor;

public class DescriptorSet {

    public final AbstractDescriptor stdin;
    public final AbstractDescriptor stdout;
    public final AbstractDescriptor stderr;

    public void redirect(ProcessBuilder processBuilder) throws ExecutionException {
        stdin.redirect(IDescriptor.SystemType.stdin, processBuilder);
        stdout.redirect(IDescriptor.SystemType.stdout, processBuilder);
        stderr.redirect(IDescriptor.SystemType.stderr, processBuilder);
    }

    public DescriptorSet() {
        stdin = ShellDescriptor.stdin();
        stdout = ShellDescriptor.stdout();
        stderr = ShellDescriptor.stderr();
    }

    private DescriptorSet(AbstractDescriptor stdin, AbstractDescriptor stdout, AbstractDescriptor stderr) {
        this.stdin = stdin;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public DescriptorSet forkStdin(AbstractDescriptor descriptor) {
        return new DescriptorSet(descriptor, stdout, stderr);
    }

    public DescriptorSet forkStdout(AbstractDescriptor descriptor) {
        return new DescriptorSet(stdin, descriptor, stderr);
    }

    public DescriptorSet forkStderr(AbstractDescriptor descriptor) {
        return new DescriptorSet(stdin, stdout, descriptor);
    }

}
