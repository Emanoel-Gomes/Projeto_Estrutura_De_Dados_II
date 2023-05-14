import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEnd;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEnd = false;
    }

    public boolean containsKey(char c) {
        return this.children.containsKey(c);
    }

    public TrieNode get(char c) {
        return this.children.get(c);
    }

    public void put(char c, TrieNode node) {
        this.children.put(c, node);
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void setEnd() {
        this.isEnd = true;
    }
}

