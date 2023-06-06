import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoF extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultCategoryDataset dataset;

    public GraficoF(String title, String frequencyString) {
        super(title);
        List<WordFreq> wordFreqs = parseFrequencyString(frequencyString);
        dataset = createDataset(wordFreqs);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(chartPanel);

        // Criar botões
        JButton ordemAlfabeticaDButton = new JButton("ordemAlfabeticaD");
        JButton ordemAlfabeticaCButton = new JButton("ordemAlfabeticaC");
        JButton ordemNumDButton = new JButton("ordemNumD");
        JButton ordemNumCButton = new JButton("ordemNumC");

        // Adicionar eventos de clique aos botões
        ordemAlfabeticaDButton.addActionListener(new ButtonClickListener());
        ordemAlfabeticaCButton.addActionListener(new ButtonClickListener());
        ordemNumDButton.addActionListener(new ButtonClickListener());
        ordemNumCButton.addActionListener(new ButtonClickListener());

        // Usar FlowLayout para os botões na parte inferior
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(ordemAlfabeticaDButton);
        add(ordemAlfabeticaCButton);
        add(ordemNumDButton);
        add(ordemNumCButton);

        pack();
        setVisible(true);
    }

    private List<WordFreq> parseFrequencyString(String frequencyString) {
        List<WordFreq> wordFreqs = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+):(\\d+)");
        Matcher matcher = pattern.matcher(frequencyString);
        while (matcher.find()) {
            String word = matcher.group(1);
            int freq = Integer.parseInt(matcher.group(2));
            wordFreqs.add(new WordFreq(word, freq));
        }
        return wordFreqs;
    }

    private DefaultCategoryDataset createDataset(List<WordFreq> wordFreqs) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (WordFreq wordFreq : wordFreqs) {
            dataset.addValue(wordFreq.freq, "Frequency", wordFreq.word);
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Word Frequency",
                "Word",
                "Frequency",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false,
                true,
                true
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
    
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(0.10);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setMaximumCategoryLabelWidthRatio(0.7f);
    
        return chart;
    }
    
    private void updateDataset(DefaultCategoryDataset newDataset) {
        dataset.clear();
        for (int i = 0; i < newDataset.getRowCount(); i++) {
            Comparable<?> rowKey = newDataset.getRowKey(i);
            for (int j = 0; j < newDataset.getColumnCount(); j++) {
                Comparable<?> columnKey = newDataset.getColumnKey(j);
                Number value = newDataset.getValue(i, j);
                dataset.setValue(value, rowKey, columnKey);
            }
        }
    }
    
    class WordFreq {
        String word;
        int freq;
    
        WordFreq(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
    
    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonName = button.getText();
    
            // Verificar o nome do botão e executar a ação correspondente
            if (buttonName.equals("ordemAlfabeticaD")) {
                String ordem = ES_Arquivo.ordenarTexto(WordCounter.getFrequencia(FileChooser.getSelectedFilePath()), "ordemAlfabeticaD");
                DefaultCategoryDataset newDataset = createDataset(parseFrequencyString(ordem));
                updateDataset(newDataset);
            } else if (buttonName.equals("ordemAlfabeticaC")) {
                String ordem = ES_Arquivo.ordenarTexto(WordCounter.getFrequencia(FileChooser.getSelectedFilePath()), "ordemAlfabeticaC");
                DefaultCategoryDataset newDataset = createDataset(parseFrequencyString(ordem));
                updateDataset(newDataset);
            } else if (buttonName.equals("ordemNumD")) {
                String ordem = ES_Arquivo.ordenarTexto(WordCounter.getFrequencia(FileChooser.getSelectedFilePath()),"ordemNumD");
                DefaultCategoryDataset newDataset = createDataset(parseFrequencyString(ordem));
                updateDataset(newDataset);
            } else if (buttonName.equals("ordemNumC")) {
                String ordem = ES_Arquivo.ordenarTexto(WordCounter.getFrequencia(FileChooser.getSelectedFilePath()), "ordemNumC");
                DefaultCategoryDataset newDataset = createDataset(parseFrequencyString(ordem));
                updateDataset(newDataset);
            }
        }
    }
}    
               
