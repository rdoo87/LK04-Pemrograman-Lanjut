package LK04_Pemlan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ProtocolKeamanan pk = new ProtocolKeamanan();
        Rekening rekeningUser = null;
        RekeningValas layanan = new RekeningValas();

        boolean signUp = false;
        int pilih = 0;
        while (pilih != 3) {
            System.out.println("Selamat Datang di Bank... ");
            System.out.println("Pilih Menu : ");
            System.out.println("1. Sign Up");
            System.out.println("2. Login Rekening");
            System.out.println("3. Exit");
            pilih = in.nextInt();
            in.nextLine();

            if (pilih == 1) {
                System.out.println("Masukan Nomor Rekening : ");
                int NoRek = in.nextInt();
                in.nextLine();

                System.out.println("Masukan Nama     : ");
                String nama = in.nextLine();

                System.out.println("Masukan Password : ");
                int passwordInput = in.nextInt();

                System.out.println("Masukan PIN      : ");
                int PIN = in.nextInt();
                in.nextLine();

                int passwordSaved = pk.encrypt(passwordInput);
                int pinSaved = pk.encrypt(PIN);

                rekeningUser = new Rekening(NoRek, nama, passwordSaved, pinSaved);
                signUp = true;
                pk.logging("Sign Up Berhasil! - " + nama);
            }

            if (pilih == 2) {
                if (!signUp) {
                    System.out.println("Silahkan SignUp Terlebih Dahulu!");
                    continue;
                }

                boolean login = false;

                while (!login) {
                    System.out.println("Masukan Nama : ");
                    String nama = in.nextLine();

                    if (!nama.equals(rekeningUser.getNama())) {
                        System.out.println("Anda salah memasukan nama!");
                        continue;
                    }

                    System.out.println("Masukan Password : ");
                    int inputPassword = in.nextInt();
                    
                    int OTP = pk.GenerateOTP();
                    System.out.println("otp:"+ OTP);
                    System.out.println("Masukkan otp:");
                    int inOTP = in.nextInt();
                    if (OTP!=inOTP) {
                        System.out.println("OTP salah!");
                    }

                    if (!pk.ValidatePassword(inputPassword, rekeningUser.getPassword())) {
                        System.out.println("Password salah!");
                        in.nextLine();
                        continue;
                    }

                    System.out.println("Masukan PIN : ");
                    int PIN = in.nextInt();
                    if (!pk.validatePIN(PIN, rekeningUser.getPin())) {
                        System.out.println("Anda salah memasukan PIN!");
                        in.nextLine();
                        continue;
                    }
                    in.nextLine();

                    pk.logging("Login Berhasil");
                    System.out.println("Login Berhasil!");
                    login = true;

                    int menu = 0;
                    while (menu != 4) {
                        System.out.println("===== INFORMASI REKENING =====");
                        System.out.println("Nama    : " + rekeningUser.getNama());
                        System.out.println("Saldo   : " + rekeningUser.saldo);
                        System.out.println("==============================");
                        System.out.println("");
                        System.out.println("===== MENU NASABAH =====");
                        System.out.println("1. Top Up Saldo");
                        System.out.println("2. Transfer");
                        System.out.println("3. Cek Saldo");
                        System.out.println("4. Exit");

                        menu = in.nextInt();

                        switch (menu) {
                            case 1:
                                System.out.println("Masukan Jumlah TopUp : ");
                                long topUp = in.nextLong();
                                layanan.topUpSaldo(rekeningUser, topUp);
                                pk.logging("TopUp Berhasil");
                                System.out.println("TopUp Berhasil!");
                                break;

                            case 2:
                                System.out.println("Jenis Transfer");
                                System.out.println("1. Transfer Rupiah");
                                System.out.println("2. Transfer Valas");
                                int jenis = in.nextInt();
                                System.out.println("Rekening Tujuan : ");
                                int tujuan = in.nextInt();

                                if (pk.ValidateTransaction(rekeningUser.getNomorRekening(), tujuan)) {
                                    System.out.println("Tidak bisa transfer ke rekening sendiri!");
                                    break;
                                } 

                                System.out.println("Jumlah Transfer : ");
                                long jumlah = in.nextLong();
                                if (rekeningUser.saldo <= jumlah) {
                                    System.out.println("Saldo kamu tidak mencukupi!");
                                    continue;
                                }
                                rekeningUser.transfer(jumlah);
                                pk.logging("Transfer Berhasil!");
                                break;

                            case 3:
                                System.out.println("Saldo Anda : Rp." + rekeningUser.saldo);
                                pk.logging("Cek Saldo");
                                break;

                            case 4:
                                System.out.println("Logout berhasil!, kembali ke menu awal.");
                                pk.logging("User Logout.");
                                break;

                            default:
                                System.out.println("Menu tidak tersedia!");
                        }
                    }
                }
            }
        }
        in.close();
    }
}
