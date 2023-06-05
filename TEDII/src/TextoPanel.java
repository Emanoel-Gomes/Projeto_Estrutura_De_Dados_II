import javax.swing.*;
import java.awt.*;

public class TextoPanel {
    public static void imprimirTexto(String texto) {
        JFrame frame = new JFrame("Imprimir texto");
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(texto);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o texto horizontalmente
        textArea.setAlignmentY(Component.CENTER_ALIGNMENT); // Centraliza o texto verticalmente
        textArea.setLineWrap(true); // Faz o texto quebrar de linha automaticamente
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Define o tamanho da fonte como 12
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500)); // Define o tamanho do painel com barra de rolagem
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Define o layout do painel como BoxLayout
        panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o painel horizontalmente
        panel.setAlignmentY(Component.CENTER_ALIGNMENT); // Centraliza o painel verticalmente
        panel.add(scrollPane);
        
        frame.add(panel);
        frame.pack();
        frame.setResizable(false); // Define que o frame não pode ser redimensionado
        frame.setVisible(true);
    }
}