package Main;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Util.Input;
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

    public Bidak selectedBidak;

    Input input = new Input(this);

    // Fungsi Untuk membuat Base Papan
    public Papan() {
        this.setPreferredSize(new Dimension(cols * ukuranPetak, rows * ukuranPetak));
        this.setBackground(Color.GREEN);

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addBidak();
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
        // Move the piece
        move.bidak.col = move.newCol;
        move.bidak.row = move.newRow;
        move.bidak.xPos = move.newCol * ukuranPetak;
        move.bidak.yPos = move.newRow * ukuranPetak;

        // Capture the opponent's piece if exists
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
            listBidak.add(new Pion(this, col, 1, false));
        }

        // For Loop untuk menambahkan pion putih (baris ke-6)
        for (int col = 0; col < 8; col++) {
            listBidak.add(new Pion(this, col, 6, true));
        }

        // Meambahkan Bidak Utama Hitam
        listBidak.add(new Benteng(this, 0, 0, false, false));
        listBidak.add(new Kuda(this, 1, 0, false, true));
        listBidak.add(new Mentri(this, 2, 0, false, true));
        listBidak.add(new Ratu(this, 3, 0, false));
        listBidak.add(new Raja(this, 4, 0, false));
        listBidak.add(new Mentri(this, 5, 0, false, false));
        listBidak.add(new Kuda(this, 6, 0, false, false));
        listBidak.add(new Benteng(this, 7, 0, false, true));

        // Menambahkan Bidak Utama Putih
        listBidak.add(new Benteng(this, 0, 7, true, false));
        listBidak.add(new Kuda(this, 1, 7, true, true));
        listBidak.add(new Mentri(this, 2, 7, true, true));
        listBidak.add(new Ratu(this, 3, 7, true));
        listBidak.add(new Raja(this, 4, 7, true));
        listBidak.add(new Mentri(this, 5, 7, true, false));
        listBidak.add(new Kuda(this, 6, 7, true, false));
        listBidak.add(new Benteng(this, 7, 7, true, true));
    }

    // Fungsi untuk Memberikan 2 warna selang-seling :v
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(162, 218, 90) : new Color(97, 46, 105));
                g2d.fillRect(c * ukuranPetak, r * ukuranPetak, ukuranPetak, ukuranPetak);
            }

        // Menggambar Highlight pada Bidak yang dipilih
        if (selectedBidak != null)
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++) {
                    // Validasi jika Bidak yang dipilih bisa bergerak ke petak tersebut
                    if (isValidMove(new Move(this, selectedBidak, c, r))) {
                        g2d.setColor(new Color(255, 0, 0, 100)); // Merah transparan (RGBA)
                        g2d.fillRect(c * ukuranPetak, r * ukuranPetak, ukuranPetak, ukuranPetak);
                    }
                }

        // Menggambar Bidak
        for (Bidak bidak : listBidak) {
            bidak.paint(g2d);
        }
    }
}
