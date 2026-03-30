public interface TransaksiDigital extends Transaksi {
    void topUpSaldo(Rekening rekeningAsal, long nominal);
}
