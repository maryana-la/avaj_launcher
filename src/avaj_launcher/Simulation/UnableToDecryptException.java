package avaj_launcher.Simulation;

public class UnableToDecryptException extends Exception {
    public UnableToDecryptException(String msg) {
        super("Error: unable to decrypt. " + msg);
    }

    public UnableToDecryptException(Throwable msg) {
        super("Error: unable to decrypt. " + msg);
    }
}
