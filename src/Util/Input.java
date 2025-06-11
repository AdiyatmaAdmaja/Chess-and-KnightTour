package Util;

import java.awt.event.*;

import Main.Papan;
import pieces.Bidak;

public class Input extends MouseAdapter {

    Papan board;

    public Input(Papan board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / board.ukuranPetak;
        int row = e.getY() / board.ukuranPetak;

        Bidak bidakXY = board.getBidak(col, row);
        if (bidakXY != null) {
            board.selectedBidak = bidakXY;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (board.selectedBidak != null) {
            board.selectedBidak.xPos = e.getX() - board.ukuranPetak / 2;
            board.selectedBidak.yPos = e.getY() - board.ukuranPetak / 2;

            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (board.selectedBidak != null) {
            int col = e.getX() / board.ukuranPetak;
            int row = e.getY() / board.ukuranPetak;

            // Cek apakah posisi pelepasan berada di dalam papan (0-7)
            boolean isOutOfBounds = (col < 0 || col > 7 || row < 0 || row > 7);

            if (isOutOfBounds) {
                // Jika di luar papan, langsung kembalikan bidak ke posisi semula
                board.selectedBidak.xPos = board.selectedBidak.col * board.ukuranPetak;
                board.selectedBidak.yPos = board.selectedBidak.row * board.ukuranPetak;
            } else {
                // Jika di dalam papan, proses pergerakan seperti biasa
                Move move = new Move(board, board.selectedBidak, col, row);

                if (board.isValidMove(move)) {
                    board.makeMove(move);
                } else {
                    board.selectedBidak.xPos = board.selectedBidak.col * board.ukuranPetak;
                    board.selectedBidak.yPos = board.selectedBidak.row * board.ukuranPetak;
                }
            }
        }

        board.selectedBidak = null;
        board.repaint();
    }

}
