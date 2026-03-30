package LK04_Pemlan;

public interface LayananInternasional extends Transaksi {
    void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal, String mataUang);
}
