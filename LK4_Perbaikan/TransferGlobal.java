public interface TransferGlobal extends TransaksiDigital, LayananInternasional {
    void transaksiKonversiGlobal(String mataUangAsal, String mataUangTujuan, long nominal);
}
