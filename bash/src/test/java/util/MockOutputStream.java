package util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class MockOutputStream implements AutoCloseable {

    private final BufferedOutputStream outputStream;
    private final BufferedReader bufferedReader;

    private final PrintStream realSystemOut;

    public MockOutputStream() {
        PipedInputStream pipeInput = new PipedInputStream();
        this.bufferedReader = new BufferedReader(new InputStreamReader(pipeInput));
        try {
            this.outputStream = new BufferedOutputStream(new PipedOutputStream(pipeInput));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.realSystemOut = System.out;
        System.setOut(new PrintStream(this.outputStream, true));
    }

    public void ensureEmpty() {
        try {
            outputStream.close();
            assert (bufferedReader.lines().toList().size() == 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ensureEquals(String expected) {
        try {
            outputStream.close();
            String actual = String.join("\n", bufferedReader.lines().toList());
            assert (expected.equals(actual));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()  {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(this.realSystemOut);
        }
    }
}
