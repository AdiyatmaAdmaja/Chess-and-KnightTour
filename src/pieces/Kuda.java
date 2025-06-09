package pieces;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Main.Papan;

public class Kuda extends Bidak {

    // Fungsi untuk Membuat Kuda
    public Kuda(Papan board, int col, int row, boolean isWhite, boolean direction) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Kuda";

        // Ambil gambar kuda dari sheet
        BufferedImage kudaImage = sheet.getSubimage( 3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);

        // Mengatur Arah hadap Bidak
        if (!direction) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-kudaImage.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            kudaImage = op.filter(kudaImage, null);
        }

        // Resize sprite
        this.sprite = kudaImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.col) * Math.abs(row - this.row) == 2;
    }
}
