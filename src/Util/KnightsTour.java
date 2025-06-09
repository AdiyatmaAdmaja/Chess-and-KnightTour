package Util;

public class KnightsTour {
    private static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static boolean solveTour(int[][] board, int x, int y, int moveCount) {
        int N = board.length;
        if (moveCount == N * N) {
            return true; // Semua petak sudah dikunjungi
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isSafe(board, nextX, nextY)) {
                board[nextX][nextY] = moveCount;
                if (solveTour(board, nextX, nextY, moveCount + 1)) {
                    return true;
                }
                board[nextX][nextY] = -1; // Backtrack
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int x, int y) {
        int N = board.length;
        return x >= 0 && y >= 0 && x < N && y < N && board[x][y] == -1;
    }

    public static void main(String[] args) {
        int N = 8;
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = -1;

        // Mulai dari (0, 0)
        board[0][0] = 0;
        if (solveTour(board, 0, 0, 1)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    System.out.printf("%2d ", board[i][j]);
                System.out.println();
            }
        } else {
            System.out.println("Tidak ada solusi.");
        }
    }
}