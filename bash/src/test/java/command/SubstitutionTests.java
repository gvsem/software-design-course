package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.ast.base.AbstractExpression;
import org.example.ast.concrete.UnresolvedCommandExpression;
import org.example.execution.Executor;
import org.example.execution.context.Context;
import org.example.execution.context.Environment;
import org.example.execution.descriptor.AbstractDescriptor;
import org.example.execution.descriptor.FileDescriptor;
import org.example.execution.exception.ExecutionException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SubstitutionTests {

    @Test
    void variableSubstitution() throws ExecutionException, IOException {

        Path resultPath = Paths.get(Files.createTempFile("bashik_", "").toUri()).toAbsolutePath();
        Executor executor = new Executor();
        Context context = new Context()
                .forkOutput(new FileDescriptor(AbstractDescriptor.Type.Output, resultPath.toFile()));
        Environment environment = context.getEnvironment();
        environment.put("a", "123");
        environment.put("b", "4");
        context = context.forkEnvironment(environment);

        AbstractExpression expression = new UnresolvedCommandExpression("echo $a $a$b \"$a $b\" '$a $b'");

        expression.run(executor, context);

        String expected = "123 1234 123 4 $a $b\n";
        String actual = new String(Files.readAllBytes(resultPath));
        assertEquals(expected, actual);

    }

    @Test
    void varAssignment() throws ExecutionException, IOException {

        Path resultPath = Paths.get(Files.createTempFile("bashik_", "").toUri()).toAbsolutePath();
        Executor executor = new Executor();
        Context context = new Context()
                .forkOutput(new FileDescriptor(AbstractDescriptor.Type.Output, resultPath.toFile()));

        Stream.of("a=5", "b=4", "echo $a $b").forEach((x) -> {
            try {
                new UnresolvedCommandExpression(x).run(executor, context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        String expected = "5 4\n";
        String actual = new String(Files.readAllBytes(resultPath));
        assertEquals(expected, actual);

    }

    @Test
    void expressionSubstitution() throws ExecutionException, IOException {

        Path resultPath = Paths.get(Files.createTempFile("bashik_", "").toUri()).toAbsolutePath();
        Executor executor = new Executor();
        Context context = new Context()
                .forkOutput(new FileDescriptor(AbstractDescriptor.Type.Output, resultPath.toFile()));
        Environment environment = context.getEnvironment();
        environment.put("a", "123");
        environment.put("b", "4");
        context = context.forkEnvironment(environment);

        AbstractExpression expression = new UnresolvedCommandExpression("echo $(echo 5) $(echo $a) $(echo $b) $(echo $a$b) $(echo \"$a\") \"lol $(echo 5)\"");
        expression.run(executor, context);

        String expected = "5 123 4 1234 123 lol 5\n";
        String actual = new String(Files.readAllBytes(resultPath));
        assertEquals(expected, actual);

    }

}
