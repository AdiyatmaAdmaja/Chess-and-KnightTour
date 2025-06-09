package pieces;

import java.awt.image.BufferedImage;

import Main.Papan;

public class Ratu extends Bidak {

    // Fungsi untuk Membuat Ratu    
    public Ratu(Papan board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Ratu";

        // Ambil gambar ratu dari sheet
        BufferedImage ratuImage = sheet.getSubimage( 1 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);
        // Resize sprite
        this.sprite = ratuImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
