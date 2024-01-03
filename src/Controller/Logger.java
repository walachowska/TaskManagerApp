package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private String logFile;
    public Logger(String logFile) {
        this.logFile = logFile;
    }
    public void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFile, true))) {
            out.println(message);
        } catch (IOException e) {
            System.out.println("Error with log file");
        }
    }
}
