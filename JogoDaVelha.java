import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] botoes = new JButton[9];
    private boolean turnoX = true;

    public JogoDaVelha() {
        frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton();
            botoes[i].setFont(new Font("Arial", Font.PLAIN, 40));
            botoes[i].addActionListener(this);
            panel.add(botoes[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (turnoX) {
            botao.setText("X");
        } else {
            botao.setText("O");
        }
        botao.setEnabled(false);
        turnoX = !turnoX;

        verificarVencedor();
    }

    public void verificarVencedor() {
        // Verificar linhas
        for (int i = 0; i < 9; i += 3) {
            if (botoes[i].getText().equals(botoes[i+1].getText()) && botoes[i].getText().equals(botoes[i+2].getText()) && !botoes[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, botoes[i].getText() + " venceu!");
                reiniciarJogo();
                return;
            }
        }

        // Verificar colunas
        for (int i = 0; i < 3; i++) {
            if (botoes[i].getText().equals(botoes[i+3].getText()) && botoes[i].getText().equals(botoes[i+6].getText()) && !botoes[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, botoes[i].getText() + " venceu!");
                reiniciarJogo();
                return;
            }
        }

        // Verificar diagonais
        if (botoes[0].getText().equals(botoes[4].getText()) && botoes[0].getText().equals(botoes[8].getText()) && !botoes[0].isEnabled()) {
            JOptionPane.showMessageDialog(frame, botoes[0].getText() + " venceu!");
            reiniciarJogo();
            return;
        }
        if (botoes[2].getText().equals(botoes[4].getText()) && botoes[2].getText().equals(botoes[6].getText()) && !botoes[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, botoes[2].getText() + " venceu!");
            reiniciarJogo();
            return;
        }

        // Verificar empate
        boolean empate = true;
        for (int i = 0; i < 9; i++) {
            if (botoes[i].isEnabled()) {
                empate = false;
                break;
            }
        }
        if (empate) {
            JOptionPane.showMessageDialog(frame, "Jogo empatado!");
            reiniciarJogo();
        }
    }

    public void reiniciarJogo() {
        for (int i = 0; i < 9; i++) {
            botoes[i].setText("");
            botoes[i].setEnabled(true);
        }
        turnoX = true;
    }

    public static void main(String[] args) {
        new JogoDaVelha();
    }
}
