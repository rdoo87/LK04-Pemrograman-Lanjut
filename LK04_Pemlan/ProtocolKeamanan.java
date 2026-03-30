/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hanif Maulana
 */
import java.time.LocalDateTime;
import java.util.*;

public final class ProtocolKeamanan {
    public final int SERVER_ID = 54;

     //----------------------------Validasi pin dan password--------------------------------------
    public boolean validatePIN(int InputPin, int StoredPin) {
        int encryptedPin = encrypt(InputPin);
        if (encryptedPin == StoredPin) {
            return true;
        } else {
            return false;
        }
    }
     // kenapa aku buat dua? supaya lebih mudah dibedakan di main nya
    public boolean ValidatePassword(int password, int StoredPassword) {
        int encryptedPassword = encrypt(password);
        if (encryptedPassword == StoredPassword) {
            return true;
        } else {
            return false;
        }
    }
    //Masukin input angka (int) dan bandingin sama data yang udah kesimpen di database (String).  Input udah otomatis di-enkrip, gausah di enkrip lagi.

    //--------------------------Enkrip dan dekrip--------------------------------------------------
     
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

    //----------------------------Generate OTP-------------------------------------------------
    public int GenerateOTP() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    //------------------------------------Logging------------------------------------------
    public void logging(String pesan) {
        System.out.println("["+ LocalDateTime.now() +"] " + pesan);
        // contoh cara pake ProtocolKeamanan.logging("login success - Hamam");
        // nanti outputnya: [waktu] login success - Hamam
    }

    //----------------------------------Validasi transaksi-------------------------------------
    
    public boolean ValidateTransaction(int asalRekening, int tujuanRekening) {
        return tujuanRekening != asalRekening;
    }
    //-----------------------------------------------------------------------------------
}
