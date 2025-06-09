package Util;

public class KnightTour {
    private final int BOARD_SIZE = 8;
    private int[][] solution;
    private int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2}; // Kemungkinan langkah X kuda
    private int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1}; // Kemungkinan langkah Y kuda

    public KnightTour() {
        solution = new int[BOARD_SIZE][BOARD_SIZE];
    }

    // Fungsi utama untuk memulai pencarian solusi
    public int[][] findTour(int startX, int startY) {
        // Inisialisasi papan dengan -1
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                solution[i][j] = -1;
            }
        }

        // Langkah pertama
        solution[startY][startX] = 1;

        // Mulai langkah kedua
        if (solveKTUtil(startX, startY, 2)) {
            return solution;
        } else {
            int[][] noSolution = new int[1][1];
            noSolution[0][0] = -1;
            return noSolution;
        }
    }

    // Fungsi rekursif untuk menyelesaikan Knight's Tour
    // Menggunakan algoritma Warnsdorff untuk memilih langkah berikutnya
    private boolean solveKTUtil(int x, int y, int moveCount) {
        if (moveCount > BOARD_SIZE * BOARD_SIZE) {
            return true; // Semua petak sudah dikunjungi
        }

        // Simpan semua langkah yang mungkin dengan derajat kebebasan
        int[][] nextMoves = new int[8][3]; // {x, y, degree}
        int count = 0;

        for (int i = 0; i < 8; i++) {
            int nextX = x + xMove[i];
            int nextY = y + yMove[i];
            if (isSafe(nextX, nextY)) {
                nextMoves[count][0] = nextX;
                nextMoves[count][1] = nextY;
                nextMoves[count][2] = getDegree(nextX, nextY);
                count++;
            }
        }

        // Urutkan langkah berdasarkan degree terkecil
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (nextMoves[i][2] > nextMoves[j][2]) {
                    int[] temp = nextMoves[i];
                    nextMoves[i] = nextMoves[j];
                    nextMoves[j] = temp;
                }
            }
        }

        // Coba semua langkah sesuai urutan Warnsdorff
        for (int i = 0; i < count; i++) {
            int nextX = nextMoves[i][0];
            int nextY = nextMoves[i][1];
            solution[nextY][nextX] = moveCount;
            if (solveKTUtil(nextX, nextY, moveCount + 1)) {
                return true;
            } else {
                solution[nextY][nextX] = -1; // Backtrack
            }
        }

        return false;
    }

    // Mengembalikan jumlah langkah valid dari posisi (x, y)
    private int getDegree(int x, int y) {
        int degree = 0;
        for (int i = 0; i < 8; i++) {
            int newX = x + xMove[i];
            int newY = y + yMove[i];
            if (isSafe(newX, newY)) {
                degree++;
            }
        }
        return degree;
    }

    // Cek apakah posisi valid dan belum dikunjungi
    private boolean isSafe(int x, int y) {
        return (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && solution[y][x] == -1);
    }
}
