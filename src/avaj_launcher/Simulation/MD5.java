package avaj_launcher.Simulation;

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
    private static final int MAX_NUMBER = 100;

    public static void encryptFile(String fileName) {
        try {
            File fileIn = new File(fileName);
            Scanner sc = new Scanner(fileIn);

//            create outFile
            File fileOut = new File("scenario.MD5");
            FileWriter writer = new FileWriter(fileOut);

//            reading original
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
            String secured = new BigInteger(1, digest.digest()).toString(16);
            return secured;
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
            System.out.println(e.getMessage());
        }
        return num;

    }

    private static String decryptString(String coded) {
        String result = null;
        //todo decoding

        return result;

    }

    private static int decryptNum(String str) {
        for (int i = 0; i <= MAX_NUMBER; i++) {
            if (str.equals(encryptWord(Integer.toString(i)))) {
                return i;
            }
        }
        return -1;
    }
}
