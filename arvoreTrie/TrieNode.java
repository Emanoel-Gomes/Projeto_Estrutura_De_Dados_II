package arvoreTrie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private char character;
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;
    private TrieNode parent;

    public TrieNode(char character) {
        this.character = character;
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.parent = null;
    }

    public char getCharacter() {
        return character;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }
}
