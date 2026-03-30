package LK04_Pemlan;


public class Rekening {
    private int nomorRekening;
    long saldo;
    String prefiks;
    private String nama;
    private int password;
    private int pin;
    //constructor
    public Rekening(){}
    public Rekening (int no_rek, String nama, int pin, int password){
        nomorRekening = no_rek;
        saldo = 0;
        this.nama = nama;
        this.pin = pin;
        this.password = password;
        prefiks = (Integer.toString(nomorRekening)).substring(0,3);
    }
    public Rekening (int no_rek, String nama, long nominal, int pin, int password){
        nomorRekening = no_rek;
        saldo = nominal;
        this.nama = nama;
        this.pin = pin;
        this.password = password;
        prefiks = (Integer.toString(nomorRekening)).substring(0,3);
    }
    public Rekening (Rekening rek){
        nomorRekening = rek.getNomorRekening();
        saldo = rek.saldo;
        nama = rek.nama;
        pin = rek.pin;
        password = rek.password;
        prefiks = rek.prefiks;
    }
    
    //method
    public void isiSaldo(long nominal){
        int minimal = 0;
        String mataUang = null;
        switch (prefiks){
            case "062":minimal = 10000; mataUang = "Rp"; break;
            case "001":minimal = 5; mataUang = "$"; break;
            case "049": minimal = 5; mataUang = "Euro "; break;
            case "081": minimal = 100; mataUang = "Yen "; break;
            case "044": minimal = 5; mataUang = "Pound "; break;
            case "086": minimal = 10; mataUang = "Yuan "; break;
            default:;
        }
        if(nominal < minimal){System.out.println("nilai minimal "+mataUang+minimal);}
        else{
            saldo += nominal;
            System.out.println("Saldo Bertambah");}
    }
    public void transfer(long nominal){
        int minimal = 0;
        String mataUang = null;
        switch (prefiks){
            case "062":minimal = 20000; mataUang = "Rp"; break;
            case "001":minimal = 5; mataUang = "$"; break;
            case "049": minimal = 5; mataUang = "Euro "; break;
            case "081": minimal = 100; mataUang = "Yen "; break;
            case "044": minimal = 5; mataUang = "Pound "; break;
            case "086": minimal = 10; mataUang = "Yuan "; break;
            default:;
        }
        if(saldo < minimal){System.out.println("Saldo akhir di bawah "+mataUang+minimal);}
        else{
            saldo -= nominal;
            System.out.println("transfer berhasil!");
        }
    }
    //setter getter
    public int getNomorRekening(){
        return nomorRekening;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
}