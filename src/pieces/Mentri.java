package pieces;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Main.Papan;

public class Mentri extends Bidak {

    // Fungsi untuk Membuat Metri
    public Mentri(Papan board, int col, int row, boolean isWhite, boolean direction) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Mentri";

        // Ambil gambar mentri dari sheet
        BufferedImage mentriImage = sheet.getSubimage( 2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);

        // Mengatur Arah hadap Bidak
        if (!direction) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-mentriImage.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            mentriImage = op.filter(mentriImage, null);
        }

        // Resize sprite
        this.sprite = mentriImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
