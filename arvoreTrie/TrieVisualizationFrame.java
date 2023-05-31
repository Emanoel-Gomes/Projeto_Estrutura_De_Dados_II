package arvoreTrie;

import javax.swing.*;
import java.awt.*;

public class TrieVisualizationFrame extends JFrame {

    public TrieVisualizationFrame() {
        setTitle("Trie Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        TrieNode root = createSampleTrie();
        TriePanel triePanel = new TriePanel(root);
        add(triePanel);
    }

    private TrieNode createSampleTrie() {
        TrieNode root = new TrieNode('\0');

        // Inserir palavras na árvore Trie
        insertWord(root, "banana");
        insertWord(root, "abacaxi");
        insertWord(root, "limao");
        insertWord(root, "guarana");
        insertWord(root, "pastel");
        insertWord(root, "frigo");
        insertWord(root, "roupa");
        insertWord(root, "milho");
        insertWord(root, "abacate");
        insertWord(root, "abobora");
        insertWord(root, "cenoura");
        insertWord(root, "aba");


        return root;
    }

    private void insertWord(TrieNode root, String word) {
        TrieNode currentNode = root;

        for (char c : word.toCharArray()) {
            TrieNode child = currentNode.getChildren().get(c);
            if (child == null) {
                child = new TrieNode(c);
                child.setParent(currentNode);
                currentNode.getChildren().put(c, child);
            }
            currentNode = child;
        }

        currentNode.setEndOfWord(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrieVisualizationFrame frame = new TrieVisualizationFrame();
            frame.setVisible(true);
        });
    }
}
