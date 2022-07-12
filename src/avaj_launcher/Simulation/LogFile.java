package avaj_launcher.Simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    static BufferedWriter writer;

    static void createOutput() {
        try {
            LogFile.writer = new BufferedWriter(new FileWriter("simulation.txt"));
        } catch (IOException e) {
            Simulator.printError("Error with output file. " + e.getMessage(), -1);
        }
    }

    public static void addToFile(String str) {
        try {
            LogFile.writer.write(str);
            LogFile.writer.newLine();
            LogFile.writer.flush();
        } catch (IOException e) {
            Simulator.printError("Error writing to file. " + e.getMessage(), -1);
        }
    }

    public static void closeWriter() {
        try {
            writer.close();
        } catch(IOException e) {
            Simulator.printError("Error writing to file. " + e.getMessage(), -1);
        }
    }
}
