import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommandRunner {
    public static String call(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            if (output.length() > 0) {
                output.deleteCharAt(output.length() - 1);
            }
            process.waitFor();
            return output.toString();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }
}
