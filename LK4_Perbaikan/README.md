Pada kode sebelumnya terdapat banyak error, berikut error dan perbaikannya:
1. Package mismatch
Sebagian file pakai package LK04_Pemlan; sebagian tidak, sehingga class tidak bisa saling mengenali. Solusi: hapus semua deklarasi package agar semua file berada di default package yang sama dengan Main.java.
2. RekeningValas — hapus abstract
Di Main.java ditulis new RekeningValas(), tapi class-nya dideklarasikan abstract sehingga tidak bisa diinstansiasi. Solusi: hapus keyword abstract, tambahkan @Override yang hilang (transferLokal).
3. RekeningValas.transferInternasional — signature tidak cocok dengan interface
Interface LayananInternasional mendeklarasikan parameter String mataUang, tapi implementasinya tidak ada parameter itu. Solusi: tambahkan parameter String mataUang sesuai interface.
4. ProsesTransaksi.jalankanTransfer — tipe storedPin salah
Parameter storedPin bertipe String padahal validatePIN di ProtocolKeamanan menerima int. Solusi: ganti jadi int storedPin.
5. ProtocolKeamanan.ValidateTransaction — logika terbalik
Method ini dipakai di Main.java untuk mencegah transfer ke rekening sendiri, tapi return true ketika tujuan sama dengan asal — artinya blok if(!pk.ValidateTransaction(...)) justru akan memblokir transfer ke rekening berbeda. Solusi: balik logikanya menjadi return tujuanRekening == asalRekening (jika sama → true → tampilkan pesan "tidak bisa transfer ke rekening sendiri").
6. Main.java — in.nextLine() hilang di beberapa tempat
Setelah beberapa nextInt()/nextLong() tidak ada nextLine() untuk membersihkan buffer, menyebabkan input nama terbaca sebagai string kosong. Sudah diperbaiki di titik-titik yang relevan.
