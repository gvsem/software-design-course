package org.example.ast.sequential;

import java.io.IOException;

import org.example.ast.base.AbstractBinaryExpression;
import org.example.ast.base.AbstractExpression;
import org.example.execution.context.Context;
import org.example.execution.descriptor.ShellDescriptor;
import org.example.execution.descriptor.TempDescriptor;
import org.example.execution.exception.ExecutionException;
import org.example.interfaces.IExecutor;

/**
 * AST node corresponding to | operator in bash
 */

public class PipeExpression extends AbstractBinaryExpression {
    public PipeExpression(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public int run(IExecutor executor, Context context) throws IOException, ExecutionException {
        TempDescriptor tempStdout = new TempDescriptor();
        int returnCode = left.run(executor, context.forkOutput(tempStdout));
        if (returnCode == 0) {
            return right.run(executor, context.forkStdin(tempStdout));
        } else {
            ShellDescriptor.stderr().print("command '" + left + "' exited with code " + returnCode);
            return returnCode;
        }
    }

    // testing purpose
    public String toString() {
        return left.toString() + " | " + right.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PipeExpression that))
            return false;
        return this.toString().equals(that.toString());
    }
}
