import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Paragraph {
    public static void parag(String Dir) {
        ArrayList<String> paragraphs = new ArrayList<>();
        String fileName = "C:/scr/sys.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(Dir))) {
            StringBuilder paragraph = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) { // empty line signifies end of paragraph
                    paragraphs.add(paragraph.toString());
                    paragraph = new StringBuilder();
                } else {
                    paragraph.append(line).append("\n"); // add line to paragraph
                }
            }
            // add last paragraph (if any)
            if (paragraph.length() > 0) {
                paragraphs.add(paragraph.toString());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // print out paragraphs
        for (String paragraph : paragraphs) {
            System.out.println(paragraph);
            System.out.println("------"); // separator between paragraphs
        }
    }
}
