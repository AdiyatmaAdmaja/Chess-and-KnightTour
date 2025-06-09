package pieces;

import java.awt.image.BufferedImage;

import Main.Papan;

public class Pion extends Bidak {

    // Fungsi untuk Membuat Pion
    public Pion(Papan board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Pion";

        // Ambil gambar pion dari sheet
        BufferedImage pionImage = sheet.getSubimage( 5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);
        // Resize sprite
        this.sprite = pionImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
