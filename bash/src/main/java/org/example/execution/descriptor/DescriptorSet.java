package org.example.execution.descriptor;

public class DescriptorSet {

    public final AbstractDescriptor stdin = ShellDescriptor.stdin();
    public final AbstractDescriptor stdout = ShellDescriptor.stdout();

    public final AbstractDescriptor stderr = ShellDescriptor.stderr();


}
