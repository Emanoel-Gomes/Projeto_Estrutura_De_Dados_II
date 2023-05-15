import java.io.IOException;
import javax.swing.JFrame;

class Main {
    public static void main(String[] args) throws IOException {
        
        // Cria a janela principal
        JFrame frame = new JFrame("Opções");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona o painel de opções à janela
        OpcoesPanel panel = new OpcoesPanel();
        frame.getContentPane().add(panel);

        // Exibe a janela
        frame.pack();
        frame.setVisible(true);

        // String frequencyString = "Brasil: 20 brasília: 10 montanha: 5 alta: 3";
        // GraficoF chart = new GraficoF("Word Frequency Chart", frequencyString);
    }
}