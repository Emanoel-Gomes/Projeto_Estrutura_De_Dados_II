import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PainelTexto painelTexto = new PainelTexto();
                PrefixTrie trie = new PrefixTrie();
    
                painelTexto.abrirArquivo.addActionListener(e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(painelTexto);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File arquivo = fileChooser.getSelectedFile();
                        try {
                            FileReader leitor = new FileReader(arquivo);
                            BufferedReader buffer = new BufferedReader(leitor);
                            String linha;
                            while ((linha = buffer.readLine()) != null) {
                                String[] palavrasArray = linha.split("[^a-zA-Z]+");
                                for (String palavra : palavrasArray) {
                                    palavra = palavra.toLowerCase();
                                    trie.insert(palavra);
                                }
                            }
                            buffer.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(painelTexto, "Erro ao abrir o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
    
                painelTexto.campoPesquisa.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        String prefixo = painelTexto.campoPesquisa.getText().toLowerCase();
                        List<String> sugestoes = trie.sugerirPalavras(prefixo);
                        painelTexto.atualizarSugestoes(sugestoes);
                    }
                });
            }
        });
    }    
}
