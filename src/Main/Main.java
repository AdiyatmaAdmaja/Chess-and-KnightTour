package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

        // Menampilkan dialog pilihan game
        String[] options = { "Catur Normal", "Knight Tour" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Selamat Datang! Silakan pilih mode permainan:",
                "Pilihan Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        // Membuat "Meja" untuk papan Catur
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(6, 96, 107));
        // MENGUBAH LAYOUT UTAMA MENJADI BORDERLAYOUT
        frame.setLayout(new BorderLayout(10, 10)); // BorderLayout dengan sedikit jarak
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat papan Catur
        Papan board;

        // Membuat Outline Papan Catur
        JPanel boardContainer = new JPanel();
        boardContainer.setLayout(new GridBagLayout()); // Gunakan GridBagLayout untuk menampung Papan agar tetap ditengah
        boardContainer.setOpaque(false); // Buat transparan agar background frame terlihat

        if (choice == 0) { // Catur Normal
            frame.setTitle("Catur Normal");
            board = new Papan("CHESS");
            boardContainer.add(board); // Tambahkan board ke container
            frame.add(boardContainer, BorderLayout.CENTER); // Tambahkan container ke frame
        } else { // Knight Tour
            frame.setTitle("Knight's Tour");
            board = new Papan("KNIGHT_TOUR");
            boardContainer.add(board); // Tambahkan board ke container
            frame.add(boardContainer, BorderLayout.CENTER); // Tambahkan container ke frame

            // --- BAGIAN BARU: Panel untuk Tombol Restart ---
            JPanel controlPanel = new JPanel();
            controlPanel.setBackground(new Color(6, 96, 107));

            JButton restartButton = new JButton("Restart Tour");
            restartButton.setFont(new Font("Arial", Font.BOLD, 16));
            restartButton.setFocusable(false);

            // Tambahkan Aksi untuk tombol restart
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.restartKnightTour(); // Panggil metode restart di Papan
                }
            });

            controlPanel.add(restartButton);

            // Tambahkan panel kontrol ke bagian bawah frame
            frame.add(controlPanel, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(frame, "Silakan klik petak mana saja untuk memulai Knight's Tour.",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}