public class RekeningValas extends Rekening implements TransferGlobal {
    double hasilKonversi = 0;
    String[] prefiks = {"062", "001", "049", "081", "044", "086"};

    @Override
    public void topUpSaldo(Rekening rekeningAsal, long nominal) {
        rekeningAsal.saldo += nominal;
    }

    @Override
    public void transferLokal(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal) {
        rekeningAsal.transfer(nominal);
        rekeningTujuan.isiSaldo(nominal);
    }

    @Override
    public void transaksiKonversiGlobal(String mataUangAsal, String mataUangTujuan, long nominal) {
        switch (mataUangAsal) {
            case "062": mataUangAsal = "IDR"; break;
            case "001": mataUangAsal = "USD"; break;
            case "049": mataUangAsal = "Euro"; break;
            case "081": mataUangAsal = "Yen"; break;
            case "044": mataUangAsal = "Poundsterling"; break;
            case "086": mataUangAsal = "RMB"; break;
            default: System.out.println("error, mata uang tidak ditemukan"); return;
        }
        switch (mataUangTujuan) {
            case "062": mataUangTujuan = "IDR"; break;
            case "001": mataUangTujuan = "USD"; break;
            case "049": mataUangTujuan = "Euro"; break;
            case "081": mataUangTujuan = "Yen"; break;
            case "044": mataUangTujuan = "Poundsterling"; break;
            case "086": mataUangTujuan = "RMB"; break;
            default: System.out.println("error, mata uang tidak ditemukan"); return;
        }

        switch (mataUangAsal) {
            case "IDR":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal; break;
                    case "USD": hasilKonversi = nominal / 17000.0; break;
                    case "Euro": hasilKonversi = nominal / 19500.0; break;
                    case "Yen": hasilKonversi = nominal / 100.0; break;
                    case "Poundsterling": hasilKonversi = nominal / 22500.0; break;
                    case "RMB": hasilKonversi = nominal / 2500.0; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            case "USD":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal * 17000; break;
                    case "USD": hasilKonversi = nominal; break;
                    case "Euro": hasilKonversi = nominal * 0.87; break;
                    case "Yen": hasilKonversi = nominal * 159; break;
                    case "Poundsterling": hasilKonversi = nominal * 0.75; break;
                    case "RMB": hasilKonversi = nominal * 7; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            case "Euro":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal * 19500; break;
                    case "USD": hasilKonversi = nominal * 1.15; break;
                    case "Euro": hasilKonversi = nominal; break;
                    case "Yen": hasilKonversi = nominal * 184; break;
                    case "Poundsterling": hasilKonversi = nominal * 0.87; break;
                    case "RMB": hasilKonversi = nominal * 8; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            case "Yen":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal * 106; break;
                    case "USD": hasilKonversi = nominal * 0.0063; break;
                    case "Euro": hasilKonversi = nominal * 0.0054; break;
                    case "Yen": hasilKonversi = nominal; break;
                    case "Poundsterling": hasilKonversi = nominal * 0.0047; break;
                    case "RMB": hasilKonversi = nominal * 0.043; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            case "Poundsterling":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal * 22500; break;
                    case "USD": hasilKonversi = nominal * 1.33; break;
                    case "Euro": hasilKonversi = nominal * 1.15; break;
                    case "Yen": hasilKonversi = nominal * 212; break;
                    case "Poundsterling": hasilKonversi = nominal; break;
                    case "RMB": hasilKonversi = nominal * 9.17; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            case "RMB":
                switch (mataUangTujuan) {
                    case "IDR": hasilKonversi = nominal * 2400; break;
                    case "USD": hasilKonversi = nominal * 0.14; break;
                    case "Euro": hasilKonversi = nominal * 0.13; break;
                    case "Yen": hasilKonversi = nominal * 23.12; break;
                    case "Poundsterling": hasilKonversi = nominal * 0.11; break;
                    case "RMB": hasilKonversi = nominal; break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                } break;
            default:
                System.out.println("error, prefiks tidak diketahui.");
        }
    }

    @Override
    public void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal, String mataUang) {
        transaksiKonversiGlobal(rekeningAsal.prefiks, rekeningTujuan.prefiks, nominal);
        rekeningAsal.transfer(nominal);
        rekeningTujuan.isiSaldo(Math.round(hasilKonversi));
    }
}
