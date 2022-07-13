package avaj_launcher.Utils;

public class UnableToDecryptException extends Exception {
    public UnableToDecryptException(String msg) {
        super("Error: unable to decrypt. " + msg);
    }

    public UnableToDecryptException(Throwable msg) {
        super("Error: unable to decrypt. " + msg);
    }
}
