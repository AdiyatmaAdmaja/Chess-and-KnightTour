package Main;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        // Membuat "Meja" untuk papan Catur
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(6, 96, 107));;
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        // Membuat papan Catur
        Papan board = new Papan();
        
        // Membuat Outline Papan Catur
        JPanel boardContainer = new JPanel();
        boardContainer.setLayout(new BorderLayout()); // Gunakan BorderLayout untuk menampung Papan
        boardContainer.setBackground(Color.WHITE); // Bisa diatur untuk melihat batasnya

        // Tambahkan Papan Catur ke dalam JPanel baru
        boardContainer.add(board, BorderLayout.CENTER);

        // Buat Border yang akan diaplikasikan ke JPanel penampung
        Border externalBorder = BorderFactory.createLineBorder(new Color(246, 188, 41), 5); // Contoh: border merah tebal 5 piksel
        boardContainer.setBorder(externalBorder);

        // Menambahkan boardContainer ke "Meja"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        frame.add(boardContainer, gbc);

        // Menambahkan Papan Catur ke "Meja"
        frame.setMinimumSize(new Dimension(800, 800)); // Ukuran minimum frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
