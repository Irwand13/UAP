# CekelDuit
Aplikasi Pencatatan Keuangan Pribadi Berbasis Java Swing

## Deskripsi
CekelDuit adalah aplikasi desktop berbasis Java Swing untuk mencatat
pemasukan dan pengeluaran secara sederhana. Data disimpan secara
persisten menggunakan file JSON tanpa database eksternal.

## Teknologi
- Java (JDK 17+)
- Java Swing
- Gson 2.10.1
- JSON (File-based storage)

## Cara Menjalankan
1. Pastikan Java sudah terinstal
2. Tambahkan library gson-2.10.1.jar
3. Jalankan file Main.java

## Cara Login
- Masukkan ID dan Nama
- Jika ID & Nama sama → data lama direload
- Jika baru → dibuat user baru

## Fitur
- Input pemasukan dan pengeluaran
- Saldo otomatis terhitung
- Riwayat transaksi dalam tabel
- Edit & hapus transaksi
- Penyimpanan otomatis ke JSON

## Format Waktu
Tanggal dan jam transaksi disimpan otomatis
dengan format:
yyyy-MM-dd HH:mm

## Penyimpanan Data
Data disimpan di:
data/users.json

## Pengembang
Nama: Irwanda Agus  
Universitas: Universitas Muhammadiyah Malang  
Mata Kuliah: Pemrograman Lanjut
