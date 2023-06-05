import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PainelTexto painelTexto = new PainelTexto();
                PrefixTrie trie = new PrefixTrie();
    
                // Exemplo de string contendo palavras separadas por espaços
                String palavras = "apple application banana book car cat";
    
                // Dividir a string em palavras individuais usando o espaço como separador
                String[] palavrasArray = palavras.split(" ");
    
                // Inserir cada palavra na Trie
                for (String palavra : palavrasArray) {
                    trie.insert(palavra);
                }
    
                // Exibir sugestões de palavras à medida que o usuário digita no campo de pesquisa
                painelTexto.campoPesquisa.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        String prefixo = painelTexto.campoPesquisa.getText();
                        List<String> sugestoes = trie.sugerirPalavras(prefixo);
                        painelTexto.atualizarSugestoes(sugestoes);
                    }
                });
            }
        });
    }    
}
