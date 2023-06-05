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

                // Inserir palavras na Trie
                trie.insert("apple");
                trie.insert("application");
                trie.insert("banana");
                trie.insert("book");
                trie.insert("car");
                trie.insert("cat");

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
