# Smart Banking System - LK04 Pemrograman Lanjutan
Bank "GakMauRugi" sedang membangun sistem transaksi yang sangat aman namun fleksibel. Anda 
diminta untuk mengimplementasikan rancangan berikut:
1. Hirarki Layanan (Interface Inheritance):
o Ada antarmuka dasar bernama Transaksi.
o Ada antarmuka TransaksiDigital yang mewarisi Transaksi.
o Ada antarmuka LayananInternasional yang mewarisi Transaksi.
Ada antarmuka TransferGlobal yang mewarisi kedua antarmuka di atas (TransaksiDigital dan 
LayananInternasional). Ini adalah titik multiple inheritance.
2. Struktur Rekening (Class Inheritance):
o Sebuah superclass bernama Rekening yang menyimpan nomorRekening dan saldo.
o Sebuah subclass bernama RekeningValas (Valuta Asing) yang mewarisi Rekening dan 
mengimplementasikan antarmuka TransferGlobal.
3. Keamanan Enkripsi (Final Class & Variable):
o Setiap transaksi harus divalidasi oleh kelas ProtokolKeamanan yang bersifat final.
o Di dalam kelas tersebut, terdapat variabel final ID_SERVER yang tidak boleh diubah sejak 
inisialisasi.
# Author
Kelompok 6 / Kelas C :

1. Abraham Samuelson Siregar [255150200111030]
2. Muhammad Dzaky Nuril Mubin [255150201111019]
3. Ridho Alfarizi [255150207111039]
4. Hanif Maulana [255150207111042]
5. Hamam Yusuf Abdulloh [255150207111050]
