
package LK04_Pemlan;

public class ProsesTransaksi {

    // ----------------------------Instansiasi
    // Keamanan--------------------------------------
    private ProtocolKeamanan protokolKeamanan;

    public ProsesTransaksi() {
        this.protokolKeamanan = new ProtocolKeamanan();
    }

    // ----------------------------Eksekusi
    // Transaksi----------------------------------------
    public boolean jalankanTransfer(int rekAsal, int rekTujuan, long nominal, int inputPin, String storedPin) {

        // Cek PIN ke class ProtocolKeamanan
        if (!protokolKeamanan.validatePIN(inputPin, storedPin)) {
            System.out.println("Gagal");
            return false;
        }

        // Cek validitas rekening
        if (!protokolKeamanan.ValidateTransaction(rekAsal, rekTujuan)) {
            System.out.println("Gagal");
            return false;
        }

        // Eksekusi transfer kalau validasi di atas lolos
        System.out.println("Transfer " + nominal + " ke " + rekTujuan);

        // Catat history transaksi pakek method logging
        protokolKeamanan.logging("Transfer sukses dari " + rekAsal + " ke " + rekTujuan);

        return true;
    }
}
