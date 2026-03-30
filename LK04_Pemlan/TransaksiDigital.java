package LK04_Pemlan;

public interface TransaksiDigital extends Transaksi {
    void topUpSaldo(Rekening rekeningAsal, long nominal);
}
