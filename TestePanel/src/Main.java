

public class Main {
    public static void main(String[] args) {
        GraficoPizza graficoPizza = new GraficoPizza(50);

        graficoPizza.adicionarDado("Dado 1", 3.0);
        graficoPizza.adicionarDado("Dado 2", 4.0);
        graficoPizza.adicionarDado("Dado 3", 2.0);
        graficoPizza.adicionarDado("Dado 4", 1.0);

        graficoPizza.imprimirGrafico();
    }
}
