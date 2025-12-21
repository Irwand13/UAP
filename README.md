💰 CekelDuit

Aplikasi Pencatatan Keuangan Pribadi Berbasis Java Swing

📌 Deskripsi Aplikasi

CekelDuit adalah aplikasi desktop berbasis Java Swing yang digunakan untuk mencatat dan memantau pemasukan serta pengeluaran keuangan pribadi.
Aplikasi ini dirancang dengan konsep sederhana, interaktif, dan user-friendly, serta menyimpan data secara persisten menggunakan file JSON.

Aplikasi ini dibuat sebagai projek Ujian Akhir Praktikum (UAP) mata kuliah Pemrograman Lanjut.

🛠 Teknologi yang Digunakan

Bahasa Pemrograman: Java (JDK 17+ / JDK 21+ / JDK 25)

GUI Framework: Java Swing

Penyimpanan Data: JSON

Library Eksternal:

Gson 2.10.1 (Serialisasi & Deserialisasi JSON)

📂 Struktur Folder (Ringkas)
CekelDuit/
├── model/
│   ├── User.java
│   ├── Transaction.java
│   └── UserRepository.java
├── ui/
│   ├── LoginFrame.java
│   ├── MainFrame.java
│   ├── HomePanel.java
│   ├── TransactionPanel.java
│   └── HistoryPanel.java
├── util/
│   ├── DateUtil.java
│   └── CurrencyUtil.java
├── data/
│   └── users.json
└── Main.java

▶️ Cara Menjalankan Program
1️⃣ Persiapan

Pastikan Java JDK sudah terinstal

java -version


Pastikan file library:

gson-2.10.1.jar

2️⃣ Menjalankan Melalui IDE (Disarankan)

Buka project di IntelliJ IDEA / NetBeans

Tambahkan gson-2.10.1.jar ke Project Libraries

Jalankan file:

Main.java

3️⃣ Menjalankan Melalui Command Line
javac -cp gson-2.10.1.jar *.java
java -cp .;gson-2.10.1.jar CekelDuit.Main


⚠️ Untuk Linux / MacOS gunakan : bukan ;

🔐 Cara Login Aplikasi

Masukkan ID User dan Nama

Jika:

ID & Nama belum ada → dibuatkan user baru

ID & Nama sama → data lama otomatis direload

📁 Data disimpan di:

data/users.json

✨ Fitur Utama Aplikasi
🏠 1. Dashboard (Home)

Menampilkan:

Nama pengguna

Saldo terkini

Ringkasan transaksi terbaru

Tombol cepat:

➕ Tambah Pemasukan

➖ Tambah Pengeluaran

💵 2. Input Transaksi

Menambahkan:

Pemasukan

Pengeluaran

Field:

Nominal

Kategori

Catatan

Otomatis menyimpan:

Tanggal & jam transaksi

📊 3. Riwayat Transaksi

Menampilkan data dalam bentuk tabel

Kolom:

Tipe Transaksi

Kategori

Nominal

Catatan

Tanggal & Jam

Fitur:

Edit transaksi

Hapus transaksi

💾 4. Penyimpanan Data Otomatis

Data disimpan dalam format JSON

Tetap tersedia meskipun aplikasi ditutup

Tidak menggunakan database eksternal

🎨 5. Antarmuka Modern

Warna lembut & clean

Font Segoe UI

Layout berbasis Card UI

Responsif dan mudah digunakan

📌 Penutup

Aplikasi CekelDuit dibuat untuk memenuhi kebutuhan pencatatan keuangan sederhana sekaligus sebagai penerapan konsep:

OOP (Object Oriented Programming)

MVC sederhana

File-based persistence

Java Swing UI

