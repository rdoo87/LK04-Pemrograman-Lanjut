public class ProsesTransaksi {

    private ProtocolKeamanan protokolKeamanan;

    public ProsesTransaksi() {
        this.protokolKeamanan = new ProtocolKeamanan();
    }

    public boolean jalankanTransfer(int rekAsal, int rekTujuan, long nominal, int inputPin, int storedPin) {

        if (!protokolKeamanan.validatePIN(inputPin, storedPin)) {
            System.out.println("Gagal");
            return false;
        }

        if (protokolKeamanan.ValidateTransaction(rekAsal, rekTujuan)) {
            System.out.println("Gagal");
            return false;
        }

        System.out.println("Transfer " + nominal + " ke " + rekTujuan);
        protokolKeamanan.logging("Transfer sukses dari " + rekAsal + " ke " + rekTujuan);
        return true;
    }
}
