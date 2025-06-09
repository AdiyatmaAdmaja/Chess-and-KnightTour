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

        int col = e.getX() / board.ukuranPetak;
        int row = e.getY() / board.ukuranPetak;

        if (board.selectedBidak != null) {
            Move move = new Move(board, board.selectedBidak, col, row);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedBidak.xPos = board.selectedBidak.col * board.ukuranPetak;
                board.selectedBidak.yPos = board.selectedBidak.row * board.ukuranPetak;
            }

        }

        board.selectedBidak = null;
        board.repaint();
    }

}
