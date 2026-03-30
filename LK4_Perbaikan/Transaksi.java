public interface Transaksi {
    void transferLokal(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal);
}
