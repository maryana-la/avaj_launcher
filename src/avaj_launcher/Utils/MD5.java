package avaj_launcher.Utils;

import avaj_launcher.Simulation.Simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class MD5 {
    private static final String BALOON = "994736b4f0aec72f6e5ae580051d012f";
    private static final String HELICOPTER = "2ab8b43468e8b92b0fc5c81e70e35a2d";
    private static final String JETPLANE = "554cd647d6b135f7e36ab1214c5e816a";
    private static final int MAX_NUMBER = 1000;
    private static final int MIN_NUMBER = -1000;

    public static void encryptFile(String fileName) {
        try {
            File fileIn = new File(fileName);
            Scanner sc = new Scanner(fileIn);
            File fileOut = new File("scenario.MD5");
            FileWriter writer = new FileWriter(fileOut);

            String buffer;
            buffer = Integer.toString(sc.nextInt());
            writer.write(encryptWord(buffer) + "\n");

            while(sc.hasNextLine()) {
                buffer = sc.nextLine();
                if (buffer.length() == 0)
                    continue;
                String [] data = buffer.split(" ");
                for (String datum : data) {
                    writer.write(encryptWord(datum) + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            Simulator.printError("Encryption file. " + e.getMessage(), -1);
        } catch (Exception e) {
            Simulator.printError("Encryption. " + e.getMessage(), -1);
        }
    }

    private static String encryptWord(String password) {
        if (password == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int decryptFile(File file, ArrayList<String> aircraftsToString) {
        int num = 0;
        try {
            Scanner sc = new Scanner(file);
            num = decryptNum(sc.nextLine());

            while (sc.hasNextLine()) {
                aircraftsToString.add(decryptString(sc.nextLine()));
            }
        } catch (Exception e) {
            Simulator.printError(e.getMessage(), -1);
        }
        return num;

    }

    private static String decryptString(String coded) throws UnableToDecryptException {
        String result = "";
        String[] splitted = coded.split(" ");
        if (splitted.length != 5)
            throw new UnableToDecryptException("Wrong amount of arguments");
        result += (getAircraftType(splitted[0]) + " ");
        result += (getAircraftName(splitted[1]) + " ");
        result += (decryptNum(splitted[2]) + " ");
        result += (decryptNum(splitted[3]) + " ");
        result += (decryptNum(splitted[4]));
        return result;

    }

    private static String getAircraftType(String airCoded) throws UnableToDecryptException {
        switch (airCoded) {
            case BALOON:
                return "Baloon";
            case HELICOPTER:
                return "Helicopter";
            case JETPLANE:
                return "JetPlane";
            default:
                throw new UnableToDecryptException("Invalid aircraft type");
        }
    }

    private static String getAircraftName(String airCoded) throws UnableToDecryptException {
        char[] letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char letter : letters) {
            String temp;
            for (int i = 0; i <= MAX_NUMBER; i++) {
                temp = letter + Integer.toString(i);
                if (encryptWord(temp).equals(airCoded))
                    return temp;
            }
        }
        throw new UnableToDecryptException("Invalid aircraft name");
    }

    public static int decryptNum(String str) {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            if (str.equals(encryptWord(Integer.toString(i)))) {
                return i;
            }
        }
        return -1;
    }
}
