import java.io.*;
import java.util.*;

class ES_Arquivo {
    public static String texto = "";

    static void escreve(String dir) throws IOException {
        try (BufferedWriter escrevBuff = new BufferedWriter(new FileWriter(dir))) {
            String linha;
            Scanner s = new Scanner(System.in);
            System.out.println("Escreva algo: ");
            linha = s.nextLine();
            escrevBuff.append(linha + "\n");
        }
    }
    
    public static void ler(String dir) throws IOException {
        try (BufferedReader lerBuff = new BufferedReader(new FileReader(dir))) {
            String linha = "";
            while (true) {
                if (linha != null) {
                    texto += linha + "\n";
                } else {
                    break;
                }
                linha = lerBuff.readLine();
            }
        }
    }
    
    public static String getTexto() {
        return texto;
    }
}