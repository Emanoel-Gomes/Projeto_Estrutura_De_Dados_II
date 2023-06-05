import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PainelTexto extends JFrame implements ActionListener {
    private JPanel painel;
    private JTextArea areaTexto;
    JButton abrirArquivo;
    private JFileChooser fileChooser;
    private JButton btnPalavrasFrequentes;
    private JButton btnAdicionarTexto;
    private JButton btnPesquisarPalavra;
    JTextField campoPesquisa;
    private PrefixTrie trie;

    public PainelTexto() {
        setTitle("Painel de Texto");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoPesquisa = new JTextField(20); // Cria um campo de pesquisa com 20 colunas
        campoPesquisa.addActionListener(this); // Adiciona o ActionListener para capturar eventos de digitação
        painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // adiciona os botões na parte superior do painel
        JPanel botoesSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abrirArquivo = new JButton("Abrir Arquivo");
        abrirArquivo.addActionListener(this);
        btnPalavrasFrequentes = new JButton("Palavras Frequentes");
        btnPalavrasFrequentes.addActionListener(this);
        btnAdicionarTexto = new JButton("Adicionar Texto");
        btnAdicionarTexto.addActionListener(this);
        btnPesquisarPalavra = new JButton("Pesquisar Palavra");
        btnPesquisarPalavra.addActionListener(this);
        botoesSuperior.add(abrirArquivo);
        botoesSuperior.add(btnPalavrasFrequentes);
        botoesSuperior.add(btnAdicionarTexto);
        botoesSuperior.add(btnPesquisarPalavra);
        botoesSuperior.add(campoPesquisa); // Adiciona o campo de pesquisa ao painel
        painel.add(botoesSuperior, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true); // habilita a quebra de linha automática
        areaTexto.setWrapStyleWord(false); // desabilita a quebra de linha por palavras
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        painel.add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        trie = new PrefixTrie(); // Cria uma instância da árvore Trie

        add(painel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == campoPesquisa) {
            String palavraPesquisada = campoPesquisa.getText();
            List<String> sugestoes = trie.sugerirPalavras(palavraPesquisada);
            atualizarSugestoes(sugestoes);
        } else if (event.getSource() == abrirArquivo) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                try {
                    FileReader leitor = new FileReader(arquivo);
                    BufferedReader buffer = new BufferedReader(leitor);
                    String linha;
                    StringBuilder texto = new StringBuilder();
                    while ((linha = buffer.readLine()) != null) {
                        texto.append(linha).append("\n"); // adiciona a quebra de linha no final de cada linha lida
                    }
                    buffer.close();
                    areaTexto.setText(texto.toString());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (event.getSource() == btnPalavrasFrequentes) {
            // código para exibir as palavras mais frequentes
            System.out.println("Botão 'Palavras Frequentes' pressionado");
        } else if (event.getSource() == btnAdicionarTexto) {
            // código para adicionar texto à área de texto
            System.out.println("Botão 'Adicionar Texto' pressionado");
        } else if (event.getSource() == btnPesquisarPalavra) {
            String palavraPesquisada = campoPesquisa.getText();
            // código para pesquisar uma palavra na área de texto
            System.out.println("Botão 'Pesquisar Palavra' pressionado");
            System.out.println("Palavra pesquisada: " + palavraPesquisada);
        }
    }

    public void atualizarSugestoes(List<String> sugestoes) {
        areaTexto.setText(""); // Limpa o conteúdo da área de texto
    
        // Exibe as sugestões de palavras na área de texto
        for (String sugestao : sugestoes) {
            areaTexto.append(sugestao + "\n");
        }
    }
}    
