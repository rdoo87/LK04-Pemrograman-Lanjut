package LK04_Pemlan;


public abstract class RekeningValas extends Rekening implements TransferGlobal{
    double hasilKonversi = 0;
    //prefiks 3 digit pertama Rekening
    String[] prefiks = {"062","001", "049","081","044","086"}; //Indonesia IDR, USA USD, Jerman Euro, Jepang Yen, Inggris Poundsterling, China RMB
    
    @Override
    public void topUpSaldo(Rekening rekeningAsal, long nominal){
        rekeningAsal.saldo += nominal;
    }
    public void transferLokal(Rekening rekeningAsal,Rekening rekeningTujuan, long nominal){
        rekeningAsal.transfer(nominal);
        rekeningTujuan.isiSaldo(nominal);
    }    
    public void transaksiKonversiGlobal(String mataUangAsal, String mataUangTujuan, long nominal){        
        switch (mataUangAsal){
            //IDR
            case "062": mataUangAsal = "IDR";
            break;
            //USD
            case "001": mataUangAsal = "USD";
            break;
            //Euro
            case "049": mataUangAsal = "Euro";
            break;
            //Yen
            case "081": mataUangAsal = "Yen";
            break;
            //Poundsterling
            case "044": mataUangAsal = "Poundsterling";
            break;
            //RMB
            case "086": mataUangAsal = "RMB";
            break;
            default: System.out.println("error, mata uang tidak ditemukan");
            
        }
        switch (mataUangTujuan){
            //IDR
            case "062": mataUangTujuan = "IDR";
            break;
            //USD
            case "001": mataUangTujuan = "USD";
            break;
            //Euro
            case "049": mataUangTujuan = "Euro";
            break;
            //Yen
            case "081": mataUangTujuan = "Yen";
            break;
            //Poundsterling
            case "044": mataUangTujuan = "Poundsterling";
            break;
            //RMB
            case "086": mataUangTujuan = "RMB";
            break;
            default: System.out.println("error, mata uang tidak ditemukan");
            
        }
        
        //konversi
        switch (mataUangAsal) {
            case "IDR":
                //IDR
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal;
                    break;
                    case "USD": hasilKonversi = nominal / 17000;
                    break;
                    case "Euro": hasilKonversi = nominal / 19500;
                    break;
                    case "Yen": hasilKonversi = nominal / 100;
                    break;
                    case "Poundsterling": hasilKonversi = nominal / 22500;
                    break;
                    case "RMB": hasilKonversi = nominal / 2500;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;
            case "USD":
                //USD
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal * 17000;
                    break;
                    case "USD": hasilKonversi = nominal;
                    break;
                    case "Euro": hasilKonversi = nominal * 0.87;
                    break;
                    case "Yen": hasilKonversi = nominal * 159;
                    break;
                    case "Poundsterling": hasilKonversi = nominal * 0.75;
                    break;
                    case "RMB": hasilKonversi = nominal * 7;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;    
            case "Euro":
                //EURO
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal * 19500;
                    break;
                    case "USD": hasilKonversi = nominal * 1.15;
                    break;
                    case "Euro": hasilKonversi = nominal;
                    break;
                    case "Yen": hasilKonversi = nominal * 184;
                    break;
                    case "Poundsterling": hasilKonversi = nominal * 0.87;
                    break;
                    case "RMB": hasilKonversi = nominal * 8;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;    
            case "Yen":
                //YEN
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal * 106;
                    break;
                    case "USD": hasilKonversi = nominal * 0.0063;
                    break;
                    case "Euro": hasilKonversi = nominal * 0.0054;
                    break;
                    case "Yen": hasilKonversi = nominal;
                    break;
                    case "Poundsterling": hasilKonversi = nominal * 0.0047;
                    break;
                    case "RMB": hasilKonversi = nominal * 0.043;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;    
            case "Poundsterling":
                //POUNDSTERLING
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal * 22500;
                    break;
                    case "USD": hasilKonversi = nominal * 1.33;
                    break;
                    case "Euro": hasilKonversi = nominal * 1.15;
                    break;
                    case "Yen": hasilKonversi = nominal * 212;
                    break;
                    case "Poundsterling": hasilKonversi = nominal;
                    break;
                    case "RMB": hasilKonversi = nominal * 9.17;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;    
            case "RMB":
                //RMB YUAN
                switch(mataUangTujuan){
                    case "IDR": hasilKonversi = nominal * 2400;
                    break;
                    case "USD": hasilKonversi = nominal * 0.14;
                    break;
                    case "Euro": hasilKonversi = nominal * 0.13;
                    break;
                    case "Yen": hasilKonversi = nominal * 23.12;
                    break;
                    case "Poundsterling": hasilKonversi = nominal * 0.11;
                    break;
                    case "RMB": hasilKonversi = nominal;
                    break;
                    default: System.out.println("error, mata uang tidak ditemukan");
                }
            break;    
            default:
                System.out.println("error, prefiks tidak diketahui.");
        }
    }
    public void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal/*, String mataUang*/){
        
        transaksiKonversiGlobal(rekeningAsal.prefiks,rekeningTujuan.prefiks,nominal);
        rekeningAsal.transfer(nominal);
        rekeningTujuan.isiSaldo(Math.round(hasilKonversi));
    }
}