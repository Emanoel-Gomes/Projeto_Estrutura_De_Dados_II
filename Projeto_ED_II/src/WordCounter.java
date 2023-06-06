import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class WordCounter {
    
    public static void frequent(String Dir) {
        Stopwords stop = new Stopwords();
        Map<String, Integer> wordCounts = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FileChooser.getSelectedFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                                            // nao sei como, mesmo sem espaço em branco nem null, um caractre ("") era armazenado
                    if (!stop.STOPWORDS.contains(word) && !"".equals(word)) wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return Integer.compare(entry2.getValue(), entry1.getValue());
            }
        });
        
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            queue.offer(entry);
        }
        
        for (int i = 0; i < 6 && !queue.isEmpty(); i++) {
            Map.Entry<String, Integer> entry = queue.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static String getFrequencia(String dir) {
        Stopwords stop = new Stopwords();
        Map<String, Integer> wordCounts = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(dir))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!stop.STOPWORDS.contains(word) && !"".equals(word)) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
        
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return Integer.compare(entry2.getValue(), entry1.getValue());
            }
        });
        
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            queue.offer(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Integer> entry : queue) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(" ");
            i++;
            if (i > 5) {
                break;
            }
        }
        
        return sb.toString().trim();
    }
}
