package arvoreTrie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.Map;

public class TriePanel extends JPanel {
    private TrieNode root;

    private double zoomFactor = 1.0;
    private static final double ZOOM_FACTOR_CHANGE = 0.2;

    private static final int NODE_SIZE = 30;
    private static final int HORIZONTAL_GAP = 50;
    private static final int VERTICAL_GAP = 50;
    private static final Color HIGHLIGHT_COLOR = Color.RED;

    public TriePanel(TrieNode root) {
        this.root = root;

        // Adiciona o MouseWheelListener ao painel
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    // Rotação do mouse para cima (zoom in)
                    zoomIn();
                } else {
                    // Rotação do mouse para baixo (zoom out)
                    zoomOut();
                }
            }
        });
    }

    private void zoomIn() {
        zoomFactor += ZOOM_FACTOR_CHANGE;
        repaint();
    }

    private void zoomOut() {
        zoomFactor -= ZOOM_FACTOR_CHANGE;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNode(g, root, getWidth() / 2, 20, getWidth() / 2);
    }

    private void drawNode(Graphics g, TrieNode node, int x, int y, int width) {
        g.setColor(Color.WHITE);
        g.fillOval(x - (int) (NODE_SIZE / 2 * zoomFactor), y - (int) (NODE_SIZE / 2 * zoomFactor), (int) (NODE_SIZE * zoomFactor), (int) (NODE_SIZE * zoomFactor));
        g.setColor(Color.BLACK);
        g.drawOval(x - (int) (NODE_SIZE / 2 * zoomFactor), y - (int) (NODE_SIZE / 2 * zoomFactor), (int) (NODE_SIZE * zoomFactor), (int) (NODE_SIZE * zoomFactor));
        drawHighlight(g, node, x, y);

        int childCount = node.getChildren().size();
        if (childCount > 0) {
            int childWidth = width / childCount;
            int startX = x - width / 2;
            int startY = y + (int) (NODE_SIZE * zoomFactor) + (int) (VERTICAL_GAP * zoomFactor);

            int i = 0;
            for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
                int childX = startX + childWidth * i + childWidth / 2;
                int childY = startY;

                g.setColor(Color.BLACK);
                g.drawLine(x, y + (int) (NODE_SIZE / 2 * zoomFactor), childX, childY - (int) (NODE_SIZE / 2 * zoomFactor));
                drawNode(g, entry.getValue(), childX, childY, childWidth);
                i++;
            }
        }
    }

    private void drawHighlight(Graphics g, TrieNode node, int x, int y) {
        g.setColor(node.isEndOfWord() ? HIGHLIGHT_COLOR : Color.BLACK);
        g.drawString(String.valueOf(node.getCharacter()), x - (int) (5 * zoomFactor), y + (int) (5 * zoomFactor));
    }
}    
