package pieces;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Main.Papan;

public class Benteng extends Bidak {

    // Fungsi untuk Membuat Benteng
    public Benteng(Papan board, int col, int row, boolean isWhite, boolean direction) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Benteng";

        // Ambil gambar benteng dari sheet
        BufferedImage bentengImage = sheet.getSubimage( 4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);

        // Mengatur Arah hadap Bidak
        if (!direction) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-bentengImage.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            bentengImage = op.filter(bentengImage, null);
        }

        // Resize sprite
        this.sprite = bentengImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
