import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Fim{
    Boolean f = true;
    Boolean getFim(){return this.f; }
    void setFim(){this.f = false; }
}

public class Trie {
    private TrieNode root;
    private Stopwords stopwords;

    public Trie() {
        this.root = new TrieNode();
        this.stopwords = new Stopwords();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        word = word.toLowerCase();
        if (this.stopwords.STOPWORDS.contains(word)) {
            return;
        }
        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.containsKey(c)) {
                current.put(c, new TrieNode());
            }
            current = current.get(c);
        }
        current.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.containsKey(c)) {
                return null;
            }
            current = current.get(c);
        }
        return current;
    }

    
}

class Init{
    public static void addTrie(String Dir) throws IOException {
        Trie trie = new Trie();
    //    String fileName = "C:/scr/sys.txt";
    //    String fileName1 = new String(fileName.trim());
        //ler(fileName1);
        try (BufferedReader br = new BufferedReader(new FileReader(Dir))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    trie.insert(word);
                }
            }
        }
        
        Scanner palavra = new Scanner (System.in);
        String word;
        Boolean fim = true;
        //Fim fim = new Fim();
        while((fim == true)){
        System.out.println("Digite uma palavra:  ou quit para sair");
        word = palavra.nextLine();
        System.out.println("existe " + word + ":" + trie.search(word));
        if("quit".equals(word)) fim = false ;   
        }
        
        
        /*
        System.out.print("existe pnotos ");
        System.out.println(trie.startsWith("pontos")); // true
        System.out.println(trie.search("2")); // true
        System.out.println(trie.search("original")); // false
        System.out.println(trie.search("conclusão")); // false
        System.out.println(trie.search("métodos")); // false
        System.out.println(trie.search(" ")); // false
        System.out.println(trie.search("discriminadores")); // false
        */
    }
}