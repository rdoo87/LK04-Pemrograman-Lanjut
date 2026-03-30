import java.time.LocalDateTime;
import java.util.*;

public final class ProtocolKeamanan {
    public final int SERVER_ID = 54;

    public boolean validatePIN(int inputPin, int storedPin) {
        int encryptedPin = encrypt(inputPin);
        return encryptedPin == storedPin;
    }

    public boolean ValidatePassword(int password, int storedPassword) {
        int encryptedPassword = encrypt(password);
        return encryptedPassword == storedPassword;
    }

    public int encrypt(int password) {
        int key = 3;
        int encrypted = password * key;
        return encrypted + SERVER_ID;
    }

    public int decrypt(int encryptedPassword) {
        int key = 3;
        int value = (encryptedPassword / key) - SERVER_ID;
        return value;
    }

    public int GenerateOTP() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    public void logging(String pesan) {
        System.out.println("[" + LocalDateTime.now() + "] " + pesan);
    }

    public boolean ValidateTransaction(int asalRekening, int tujuanRekening) {
        return tujuanRekening == asalRekening;
    }
}
