package command;

import org.example.command.Command;
import org.example.command.embedded.EmptyCommand;
import org.example.execution.Executor;
import org.example.execution.context.Context;
import org.junit.jupiter.api.Test;
import util.MockOutputStream;

import java.util.List;

public class EmptyCommandTest {

    @Test
    void emptyCommandTest() throws Exception {

        try (MockOutputStream mockOutputStream = new MockOutputStream()) {
            Command command = new EmptyCommand(List.of(), List.of());
            Context context = new Context();

            Executor executor = new Executor();
            int code = executor.executeProcess(command, context);

            assert(code == 0);
            mockOutputStream.ensureEquals("");
        }
    }
}
