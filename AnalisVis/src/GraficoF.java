import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public GraficoF(String title, String frequencyString) {
        super(title);
        List<WordFreq> wordFreqs = parseFrequencyString(frequencyString);
        DefaultCategoryDataset dataset = createDataset(wordFreqs);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(chartPanel);
        pack();
        setVisible(true);
    }

    private List<WordFreq> parseFrequencyString(String frequencyString) {
        List<WordFreq> wordFreqs = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+):\\s*(\\d+)");
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
            false
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
    class WordFreq {
        String word;
        int freq;

        WordFreq(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
}