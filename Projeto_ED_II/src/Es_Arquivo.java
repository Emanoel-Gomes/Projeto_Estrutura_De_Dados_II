import java.io.*;
import java.util.*;

class ES_Arquivo {
    public static String texto = "";
    
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

    public static String buscarParagrafosComPalavra(String texto, String palavra) {
        String[] paragrafos = texto.split("\\.\\s"); // Divide o texto em parágrafos

        List<String> paragrafosEncontrados = new ArrayList<>();

        for (String paragrafo : paragrafos) {
            if (paragrafo.contains(palavra)) {
                paragrafosEncontrados.add(paragrafo);
            }
        }

        if (paragrafosEncontrados.isEmpty()) {
            return "Palavra não encontrada no texto.";
        } else {
            return String.join("\n\n", paragrafosEncontrados);
        }
    }

    public static String ordenarTexto(String texto, String ordem) {
        String[] palavras = texto.split(" ");
        Map<String, Integer> map = new HashMap<>();

        for (String palavra : palavras) {
            String[] partes = palavra.split(":");
            String chave = partes[0];
            int valor = Integer.parseInt(partes[1]);
            map.put(chave, valor);
        }

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(map.entrySet());

        switch (ordem) {
            case "ordemAlfabeticaD":
                Collections.sort(lista, (a, b) -> b.getKey().compareTo(a.getKey()));
                break;
            case "ordemAlfabeticaC":
                Collections.sort(lista, (a, b) -> a.getKey().compareTo(b.getKey()));
                break;
            case "ordemNumD":
                Collections.sort(lista, (a, b) -> b.getValue().compareTo(a.getValue()));
                break;
            case "ordemNumC":
                Collections.sort(lista, (a, b) -> a.getValue().compareTo(b.getValue()));
                break;
            default:
                throw new IllegalArgumentException("Ordem inválida: " + ordem);
        }

        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.append(entry.getKey()).append(":").append(entry.getValue()).append(" ");
        }

        return resultado.toString().trim();
    }
    
    public static String getTexto() {
        return texto;
    }
}