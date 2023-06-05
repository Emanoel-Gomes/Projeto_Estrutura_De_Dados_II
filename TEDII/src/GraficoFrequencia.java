import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraficoFrequencia {
    public static String criarGrafico(String texto) {
        Map<String, Integer> contagem = new HashMap<>();
        int maxContagem = 0;
        String[] pares = texto.split("\\s+");
        for (String par : pares) {
            String[] partes = par.split(":");
            String categoria = partes[0];
            int ocorrencias = Integer.parseInt(partes[1]);
            contagem.put(categoria, ocorrencias);
            if (ocorrencias > maxContagem) {
                maxContagem = ocorrencias;
            }
        }
        List<Map.Entry<String, Integer>> listaContagem = new ArrayList<>(contagem.entrySet());
        Collections.sort(listaContagem, Comparator.comparingInt(e -> -e.getValue()));
        StringBuilder sb = new StringBuilder();
        String formato = "%-" + 20 + "s| %-" + maxContagem + "s %s vezes\n";
        for (Map.Entry<String, Integer> entrada : listaContagem) {
            String categoria = entrada.getKey();
            int ocorrencias = entrada.getValue();
            String grafico = "*".repeat(ocorrencias);
            sb.append(String.format(formato, categoria, grafico, ocorrencias));
        }
        return sb.toString();
    }
    
}