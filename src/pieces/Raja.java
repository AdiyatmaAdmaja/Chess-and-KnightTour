package pieces;

import java.awt.image.BufferedImage;

import Main.Papan;

public class Raja extends Bidak {

    // Fungsi untuk Membuat Raja
    public Raja(Papan board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Raja";

        // Ambil gambar raja dari sheet
        BufferedImage rajaImage = sheet.getSubimage( 0 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);
        // Resize sprite
        this.sprite = rajaImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
