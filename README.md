# Chess - and - Knight Tour

Selamat datang di proyek Catur dan Knight's Tour, sebuah aplikasi desktop yang dibuat dengan Java Swing.

Aplikasi ini berisi dua mode permainan dalam satu paket: permainan catur standar dan visualisasi teka-teki Knight's Tour.

## Cara Memulai

1.  Jalankan file `Main.java` untuk memulai aplikasi.
2.  Sebuah dialog akan muncul, meminta Anda untuk memilih mode permainan: "Catur Normal" atau "Knight Tour".
3.  Jendela permainan akan muncul sesuai dengan mode yang Anda pilih.

## Fitur

### Mode Permainan

Aplikasi ini memiliki dua mode yang dapat dipilih saat startup.

#### 1. Catur Normal
Mode ini adalah permainan catur standar

#### 2. Knight Tour
Visualisasi dari teka-teki matematis klasik Knight's Tour.
* **Titik Awal Interaktif:** Klik pada kotak mana pun di papan untuk memilih titik awal tur.
* **Algoritma Heuristik:** Solusi tur dihitung menggunakan algoritma Warnsdorff untuk menemukan jalur secara efisien.
* **Visualisasi Jalur:** Setelah dihitung, program akan menggambar garis merah yang menghubungkan setiap langkah tur dan memberi nomor pada setiap kotak sesuai urutan langkah.
* **Tombol Restart:** Sebuah tombol "Restart Tour" disediakan untuk membersihkan papan dan memulai tur baru dari titik awal yang berbeda.

## Struktur Folder

* **`src`**: Berisi semua file kode sumber (.java).
* **`lib`**: Berisi *library* atau dependensi eksternal (jika ada).
* **`bin`**: Folder output default untuk file-file kelas yang telah dikompilasi.