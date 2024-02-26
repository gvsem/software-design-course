import org.example.command.Command;
import org.example.command.EmbeddedCommand;
import org.example.execution.Executor;
import org.example.execution.context.Context;
import org.junit.jupiter.api.Test;

import java.util.List;

import util.MockOutputStream;

public class EmbeddedExecutorTests {

//    @Test
//    void baselineTest() throws Exception {
//
//        try (MockOutputStream mockOutputStream = new MockOutputStream()) {
//            Command command = new EmbeddedCommand("echo", List.of("5"), List.of());
//            Context context = new Context();
//
//            Executor executor = new Executor();
//            int code = executor.executeProcess(command, context);
//
//            assert(code == 1);
//            mockOutputStream.ensureEquals("Can not run echo");
//        }
//
//    }

}
