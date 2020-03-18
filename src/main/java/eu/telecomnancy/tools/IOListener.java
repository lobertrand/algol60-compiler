package eu.telecomnancy.tools;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

public class IOListener {

    public static Result listen(Runnable block) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ByteArrayOutputStream byteErr = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(byteOut, true);
        PrintStream errStream = new PrintStream(byteErr, true);
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        System.setOut(outStream);
        System.setErr(errStream);
        try {
            block.run();
        } finally {
            System.setOut(oldOut);
            System.setErr(oldErr);
        }
        Result result = new Result();
        result.output = byteOut.toString();
        result.error = byteErr.toString();
        return result;
    }

    public static class Result {
        private String output;
        private String error;

        public String getError() {
            return error;
        }

        public String getOutput() {
            return output;
        }

        public Result ifOutput(Consumer<String> stringConsumer) {
            if (!output.isEmpty()) stringConsumer.accept(output);
            return this;
        }

        public Result ifOutput(Runnable runnable) {
            if (!output.isEmpty()) runnable.run();
            return this;
        }

        public Result ifNoOutput(Runnable runnable) {
            if (output.isEmpty()) runnable.run();
            return this;
        }

        public Result ifError(Consumer<String> stringConsumer) {
            if (!error.isEmpty()) stringConsumer.accept(error);
            return this;
        }

        public Result ifError(Runnable runnable) {
            if (!error.isEmpty()) runnable.run();
            return this;
        }

        public Result ifNoError(Runnable runnable) {
            if (error.isEmpty()) runnable.run();
            return this;
        }
    }
}
