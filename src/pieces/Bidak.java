package pieces;

import javax.imageio.ImageIO;

import Main.Papan;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;

// Fungsi untuk Membuat Bidak
public class Bidak {
    // Deklarasi Baris dan Kolom
    public int col, row;
    // Deklarasi Posisi X & Y
    public int xPos, yPos;
    // Deklarasi Jenis Bidak
    public boolean isWhite;
    // Deklarasi Nama Bidak
    public String Name;
    // Deklarasi value
    public int value;

    // Load Gambar Bidak-bidak
    BufferedImage sheet;
    {
        {
            try {
                InputStream stream = ClassLoader.getSystemResourceAsStream("resource/PiecesHD.png");
                if (stream == null) {
                    throw new IllegalArgumentException("File 'pieces.png' tidak ditemukan di classpath!");
                }
                sheet = ImageIO.read(stream);
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

    }
    // Membagi Gambar Pieces
    protected int sheetScale = sheet.getWidth() / 6;

    Image sprite;

    Papan board;

    // Fungsi-fungsi untuk menggabungka Bidak ke Papan
    public Bidak(Papan board) {
        this.board = board;
    }

    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(sprite, xPos, yPos, null);
    }
}
