import java.util.ArrayList;
import java.util.List;

public class PrefixTrie {
    private TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.containsKey(c)) {
                current.put(c, new TrieNode());
            }
            current = current.get(c);
        }
        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public List<String> sugerirPalavras(String prefix) {
        List<String> sugestoes = new ArrayList<>();
        TrieNode node = searchPrefix(prefix);
        if (node != null) {
            sugestoes.addAll(collectWords(node, prefix));
        }
        return sugestoes;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (current.containsKey(c)) {
                current = current.get(c);
            } else {
                return null;
            }
        }
        return current;
    }

    private List<String> collectWords(TrieNode node, String prefix) {
        List<String> words = new ArrayList<>();
        if (node.isEndOfWord()) {
            words.add(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.containsKey(c)) {
                String newPrefix = prefix + c;
                words.addAll(collectWords(node.get(c), newPrefix));
            }
        }
        return words;
    }

    private class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return children[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            children[c - 'a'] = node;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            isEndOfWord = endOfWord;
        }
    }
}
