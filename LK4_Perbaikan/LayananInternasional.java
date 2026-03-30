public interface LayananInternasional extends Transaksi {
    void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal, String mataUang);
}
