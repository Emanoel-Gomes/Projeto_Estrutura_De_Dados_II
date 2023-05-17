import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GraficoPizza {

    private final Map<String, Double> dados;
    private final char[][] pizza;
    private final DecimalFormat formatadorPorcentagem;

    public GraficoPizza(int tamanhoPizza) {
        this.dados = new HashMap<>();
        this.pizza = new char[tamanhoPizza][tamanhoPizza];
        this.formatadorPorcentagem = new DecimalFormat("#0.00%");
        inicializarPizza();
    }

    private void inicializarPizza() {
        for (int i = 0; i < pizza.length; i++) {
            for (int j = 0; j < pizza.length; j++) {
                pizza[i][j] = ' ';
            }
        }
    }

    public void adicionarDado(String nome, double valor) {
        this.dados.put(nome, valor);
    }

    public void imprimirGrafico() {
        calcularPizza();
        imprimirPizza();
    }

    private void calcularPizza() {
        double total = dados.values().stream().mapToDouble(Double::doubleValue).sum();
        double anguloTotal = 360;
        double anguloAtual = 0;
        for (Map.Entry<String, Double> entrada : dados.entrySet()) {
            String nome = entrada.getKey();
            double valor = entrada.getValue();
            double proporcao = valor / total;
            double anguloFatia = anguloTotal * proporcao;
            preencherFatia(anguloAtual, anguloAtual + anguloFatia, nome);
            anguloAtual += anguloFatia;
        }
    }

    private void preencherFatia(double anguloInicial, double anguloFinal, String nome) {
        double raio = pizza.length / 2.0;
        double xCentro = raio;
        double yCentro = raio;

        for (int i = 0; i < pizza.length; i++) {
            for (int j = 0; j < pizza.length; j++) {
                double x = i - xCentro;
                double y = j - yCentro;

                double angulo = Math.toDegrees(Math.atan2(y, x));
                if (angulo < 0) {
                    angulo += 360;
                }

                if (angulo >= anguloInicial && angulo < anguloFinal) {
                    pizza[i][j] = '#';
                }
            }
        }

        int linhaTexto = (int) Math.round(xCentro + raio * Math.cos(Math.toRadians((anguloInicial + anguloFinal) / 2)));
        int colunaTexto = (int) Math.round(yCentro + raio * Math.sin(Math.toRadians((anguloInicial + anguloFinal) / 2)));
        pizza[linhaTexto][colunaTexto] = nome.charAt(0);
    }

    private void imprimirPizza() {
        System.out.println();
        for (int i = 0; i < pizza.length; i++) {
            for (int j = 0; j < pizza.length; j++) {
                System.out.print(pizza[i][j]);
                // System.out.print(pizza[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (Map.Entry<String, Double> entrada : dados.entrySet()) {
            String nome = entrada.getKey();
            double valor = entrada.getValue();
            double proporcao = valor / dados.values().stream().mapToDouble(Double::doubleValue).sum();
            String porcentagem = formatadorPorcentagem.format(proporcao);
            System.out.println(nome + " - " + porcentagem);
        }
    }
}
    