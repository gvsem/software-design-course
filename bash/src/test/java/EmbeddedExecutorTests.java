import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.execution.Executor;
import org.example.execution.context.Context;
import org.example.execution.descriptor.AbstractDescriptor;
import org.example.execution.descriptor.FileDescriptor;
import org.example.execution.exception.ExecutionException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EmbeddedExecutorTests {

    @Test
    void baselineTest() throws Exception {

        try (MockOutputStream mockOutputStream = new MockOutputStream()) {
            Command command = new EmbeddedCommand("echo", List.of("5"), List.of());
            Context context = new Context();

            Executor executor = new Executor();
            int code = executor.executeProcess(command, context);

            assert(code == 1);
            mockOutputStream.ensureEquals("Can not run echo");
        }

    }

}
