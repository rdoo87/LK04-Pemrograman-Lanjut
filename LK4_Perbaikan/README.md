Pada kode sebelumnya terdapat banyak error, berikut error dan perbaikannya:

1. Package Mismatch
File yang terdampak: Transaksi.java, TransaksiDigital.java, TransferGlobal.java, Rekening.java, RekeningValas.java, ProsesTransaksi.java
Keenam file ini punya package LK04_Pemlan; di baris pertama, sedangkan Main.java, ProtocolKeamanan.java, dan LayananInternasional.java tidak punya package sama sekali. Akibatnya compiler menganggap mereka berada di package berbeda dan tidak bisa saling mengakses.
java// SEBELUM — baris 1 di Transaksi.java, Rekening.java, dll.
package LK04_Pemlan;

// SESUDAH — baris tersebut dihapus di semua file
(tidak ada)

2. RekeningValas — keyword abstract
File: RekeningValas.java, baris 4
java// SEBELUM
public abstract class RekeningValas extends Rekening implements TransferGlobal {

// SESUDAH
public class RekeningValas extends Rekening implements TransferGlobal {
Main.java baris 19 melakukan new RekeningValas(), sedangkan class abstract tidak bisa diinstansiasi. Sekalian ditambahkan @Override pada transferLokal yang sebelumnya tidak ada padahal wajib karena implementasi interface.

3. RekeningValas.transferInternasional — signature tidak cocok interface
File: RekeningValas.java, baris terakhir method transferInternasional
Interface LayananInternasional (baris 4) mendeklarasikan:
javavoid transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal, String mataUang);
Tapi implementasinya di RekeningValas:
java// SEBELUM — parameter String mataUang di-comment out
public void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal/*, String mataUang*/) {

// SESUDAH — parameter dikembalikan sesuai interface
public void transferInternasional(Rekening rekeningAsal, Rekening rekeningTujuan, long nominal, String mataUang) {

4. ProsesTransaksi.jalankanTransfer — tipe parameter storedPin salah
File: ProsesTransaksi.java, baris 15
java// SEBELUM — storedPin bertipe String
public boolean jalankanTransfer(int rekAsal, int rekTujuan, long nominal, int inputPin, String storedPin) {
    if (!protokolKeamanan.validatePIN(inputPin, storedPin)) { // ERROR: validatePIN minta int, bukan String

// SESUDAH — storedPin diubah jadi int
public boolean jalankanTransfer(int rekAsal, int rekTujuan, long nominal, int inputPin, int storedPin) {
    if (!protokolKeamanan.validatePIN(inputPin, storedPin)) { // OK
ProtocolKeamanan.validatePIN (baris 14) jelas menerima dua parameter int:
javapublic boolean validatePIN(int InputPin, int StoredPin) { ... }

5. ProtocolKeamanan.ValidateTransaction — logika terbalik
File: ProtocolKeamanan.java, baris 45
java// SEBELUM — return true jika BERBEDA (logika terbalik)
public boolean ValidateTransaction(int asalRekening, int tujuanRekening) {
    return tujuanRekening != asalRekening;
}

// SESUDAH — return true jika SAMA (rekening sendiri)
public boolean ValidateTransaction(int asalRekening, int tujuanRekening) {
    return tujuanRekening == asalRekening;
}
Cara pakainya di Main.java baris 103:
javaif (pk.ValidateTransaction(rekeningUser.getNomorRekening(), tujuan)) {
    System.out.println("Tidak bisa transfer ke rekening sendiri!");
    break;
}
Dengan logika != yang lama, kondisi if akan true justru saat rekening berbeda → transfer ke orang lain malah diblokir, transfer ke diri sendiri malah lolos.

6. Main.java — in.nextLine() untuk membersihkan buffer
File: Main.java, beberapa titik
nextInt() dan nextLong() tidak mengambil karakter newline \n setelah angka diinput. Jika langsung diikuti nextLine() untuk baca nama/string, yang terbaca adalah string kosong "".
java// SEBELUM — setelah nextInt() PIN di Sign Up, tidak ada nextLine()
System.out.println("Masukan PIN      : ");
int PIN = in.nextInt();
// langsung lanjut ke proses berikutnya → buffer masih kotor

// SESUDAH — ditambah nextLine() untuk bersihkan buffer
System.out.println("Masukan PIN      : ");
int PIN = in.nextInt();
in.nextLine(); // bersihkan sisa '\n'
