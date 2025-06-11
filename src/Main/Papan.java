package Main;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Util.Input;
import Util.KnightTour;
import Util.Move;
import pieces.*;

public class Papan extends JPanel {
    // Deklarasi ukuran Tiap Petak
    public int ukuranPetak = 100;
    // Deklarasi Jumlah Baris dan Kolom
    int cols = 8;
    int rows = 8;

    // Deklarasi Array of Bidak
    ArrayList<Bidak> listBidak = new ArrayList<>();

    public Bidak selectedBidak; //

    Input input = new Input(this); //

    // Variabel untuk mode game dan hasil Knight Tour
    private String gameMode;
    private int[][] knightTourSolution;
    private KnightTour ktLogic;
    private boolean tourStarted = false;

    // Konstruktor untuk menerima mode permainan
    public Papan(String gameMode) {
        this.gameMode = gameMode;
        this.setPreferredSize(new Dimension(cols * ukuranPetak, rows * ukuranPetak));

        // Listener mouse standar untuk catur
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        if (gameMode.equals("CHESS")) {
            addBidak();
        } else if (gameMode.equals("KNIGHT_TOUR")) {
            ktLogic = new KnightTour();
            // listener mouse khusus untuk memulai Knight Tour
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    knightTourMousePressed(evt);
                }
            });
        }
    }

    // Handler klik mouse khusus untuk mode Knight Tour
    private void knightTourMousePressed(java.awt.event.MouseEvent evt) {
        // Hanya berjalan jika tour belum dimulai untuk mencegah klik berulang
        if (tourStarted || !gameMode.equals("KNIGHT_TOUR"))
            return;

        int col = evt.getX() / ukuranPetak;
        int row = evt.getY() / ukuranPetak;

        // Jangan proses jika klik di luar papan
        if (col < 0 || col >= cols || row < 0 || row >= rows)
            return;

        knightTourSolution = ktLogic.findTour(col, row);
        tourStarted = true;

        // Cek jika solusi tidak ditemukan
        if (knightTourSolution.length == 1 && knightTourSolution[0][0] == -1) {
            JOptionPane.showMessageDialog(this, "Tidak ditemukan solusi dari titik awal ini!", "Info",
                    JOptionPane.WARNING_MESSAGE);
            tourStarted = false; // Izinkan pengguna mencoba lagi dari titik lain
        }

        repaint();
    }

    public void restartKnightTour() {
        this.knightTourSolution = null;
        this.tourStarted = false;

        // Beri notifikasi kepada user
        JOptionPane.showMessageDialog(this, "Papan telah di-reset. Silakan klik petak untuk memulai lagi.", "Restart",
                JOptionPane.INFORMATION_MESSAGE);

        // Gambar ulang papan untuk membersihkan jejak tour sebelumnya
        repaint();
    }

    // Fungsi untuk memilih Bidak
    public Bidak getBidak(int col, int row) {

        for (Bidak bidak : listBidak) {
            if (bidak.col == col && bidak.row == row) {
                return bidak;
            }
        }

        return null;
    }

    // Fungsi untuk bergerak
    public void makeMove(Move move) {

        move.bidak.col = move.newCol;
        move.bidak.row = move.newRow;
        move.bidak.xPos = move.newCol * ukuranPetak;
        move.bidak.yPos = move.newRow * ukuranPetak;

        // Menangkap bidak lawan jika ada
        if (move.capture != null) {
            capture(move);
        }
    }

    // Fungsi untuk Menangkap
    public void capture(Move move) {
        listBidak.remove(move.capture);
    }

    // Fungsi untuk mencegah bidak Saling nangkap antar teman
    public Boolean isValidMove(Move move) {
        if (sameTeam(move.bidak, move.capture)) {
            return false;
        }
        if (!move.bidak.isValidMovement(move.newCol, move.newRow)) {
            return false;
        }
        if (move.bidak.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false;
        }

        return true;
    }

    // Fungsi untuk apakah Bidak pada tim yg sama
    public Boolean sameTeam(Bidak p1, Bidak p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    // Fungsi untuk menambahkan Bidak
    public void addBidak() {

        // For Loop untuk menambahkan pion hitam (baris ke-1)
        for (int col = 0; col < 8; col++) {
            listBidak.add(new Pion(this, col, 1, false)); //
        }

        // For Loop untuk menambahkan pion putih (baris ke-6)
        for (int col = 0; col < 8; col++) {
            listBidak.add(new Pion(this, col, 6, true)); //
        }

        // Meambahkan Bidak Utama Hitam
        listBidak.add(new Benteng(this, 0, 0, false, false)); //
        listBidak.add(new Kuda(this, 1, 0, false, true)); //
        listBidak.add(new Mentri(this, 2, 0, false, true)); //
        listBidak.add(new Ratu(this, 3, 0, false)); //
        listBidak.add(new Raja(this, 4, 0, false)); //
        listBidak.add(new Mentri(this, 5, 0, false, false)); //
        listBidak.add(new Kuda(this, 6, 0, false, false)); //
        listBidak.add(new Benteng(this, 7, 0, false, true)); //
        // Menambahkan Bidak Utama Putih
        listBidak.add(new Benteng(this, 0, 7, true, false)); //
        listBidak.add(new Kuda(this, 1, 7, true, true)); //
        listBidak.add(new Mentri(this, 2, 7, true, true)); //
        listBidak.add(new Ratu(this, 3, 7, true)); //
        listBidak.add(new Raja(this, 4, 7, true)); //
        listBidak.add(new Mentri(this, 5, 7, true, false)); //
        listBidak.add(new Kuda(this, 6, 7, true, false)); //
        listBidak.add(new Benteng(this, 7, 7, true, true)); //
    }

    // Fungsi untuk Memberikan 2 warna selang-seling dan menggambar semua elemen
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Panggil super method agar background tergambar
        Graphics2D g2d = (Graphics2D) g;

        // Gambar papan catur
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(162, 218, 90) : new Color(97, 46, 105)); //
                g2d.fillRect(c * ukuranPetak, r * ukuranPetak, ukuranPetak, ukuranPetak); //
            }

        // Logika gambar berdasarkan mode permainan
        if (gameMode.equals("CHESS")) {
            // Menggambar Highlight pada Bidak yang dipilih
            if (selectedBidak != null)
                for (int r = 0; r < rows; r++)
                    for (int c = 0; c < cols; c++) {
                        // Validasi jika Bidak yang dipilih bisa bergerak ke petak tersebut
                        if (isValidMove(new Move(this, selectedBidak, c, r))) {
                            g2d.setColor(new Color(255, 0, 0, 100)); // Merah transparan (RGBA)
                            g2d.fillRect(c * ukuranPetak, r * ukuranPetak, ukuranPetak, ukuranPetak); //
                        }
                    }
            // Menggambar Bidak
            for (Bidak bidak : listBidak) {
                bidak.paint(g2d);
            }
        } else if (gameMode.equals("KNIGHT_TOUR") && knightTourSolution != null && tourStarted) {
            drawKnightTourSolution(g2d);
        }
    }

    // Fungsi baru untuk menggambar garis dan angka dari hasil Knight Tour
    private void drawKnightTourSolution(Graphics2D g2d) {
        Point[] path = new Point[65]; // Array untuk menyimpan koordinat (x,y) dari setiap langkah
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (knightTourSolution[r][c] != -1) {
                    path[knightTourSolution[r][c]] = new Point(c, r);
                }
            }
        }

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3)); // Mengatur ketebalan garis

        // Gambar garis yang menghubungkan setiap langkah dari 1 ke 64
        for (int i = 1; i < 64; i++) {
            if (path[i] != null && path[i + 1] != null) {
                int x1 = path[i].x * ukuranPetak + ukuranPetak / 2;
                int y1 = path[i].y * ukuranPetak + ukuranPetak / 2;
                int x2 = path[i + 1].x * ukuranPetak + ukuranPetak / 2;
                int y2 = path[i + 1].y * ukuranPetak + ukuranPetak / 2;
                g2d.drawLine(x1, y1, x2, y2);
            }
        }

        // Gambar angka urutan langkah pada setiap petak
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 1; i <= 64; i++) {
            if (path[i] != null) {
                String num = Integer.toString(i);
                // Center the number in the square
                int x = path[i].x * ukuranPetak + (ukuranPetak - fm.stringWidth(num)) / 2;
                int y = path[i].y * ukuranPetak + (ukuranPetak - fm.getHeight()) / 2 + fm.getAscent();
                g2d.drawString(num, x, y);
            }
        }

        // Gambar sprite kuda di posisi awal (langkah ke-1)
        try {
            if (path[1] != null) {
                Image knightImage = new Kuda(this, path[1].x, path[1].y, true, true).sprite; //
                g2d.drawImage(knightImage, path[1].x * ukuranPetak, path[1].y * ukuranPetak, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}