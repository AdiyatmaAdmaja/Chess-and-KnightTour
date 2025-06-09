package Util;

import Main.Papan;
import pieces.Bidak;

public class Move {

    // Deklarasi variabel Posisi Lama & Baru
    public int oldCol, oldRow, newCol, newRow;

    // Deklarasi Variabel Piece
    public Bidak bidak, capture;

    public Move(Papan board, Bidak bidak, int newCol, int newRow) {
        
        this.oldCol = bidak.col;
        this.oldRow = bidak.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.bidak = bidak;
        this.capture = board.getBidak(newCol, newRow);
    }
}