package arvoreTrie;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TriePanel extends JPanel {
    private TrieNode root;

    private static final int NODE_SIZE = 30;
    private static final int HORIZONTAL_GAP = 50;
    private static final int VERTICAL_GAP = 50;
    private static final Color HIGHLIGHT_COLOR = Color.RED;

    public TriePanel(TrieNode root) {
        this.root = root;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNode(g, root, getWidth() / 2, 20, getWidth() / 2);
    }

    private void drawNode(Graphics g, TrieNode node, int x, int y, int width) {
        g.setColor(Color.WHITE);
        g.fillOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        drawHighlight(g, node, x, y);

        int childCount = node.getChildren().size();
        if (childCount > 0) {
            int childWidth = width / childCount;
            int startX = x - width / 2;
            int startY = y + NODE_SIZE / 2 + VERTICAL_GAP;

            int i = 0;
            for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
                int childX = startX + childWidth * i + childWidth / 2;
                int childY = startY;

                g.setColor(Color.BLACK);
                g.drawLine(x, y + NODE_SIZE / 2, childX, childY - NODE_SIZE / 2);
                drawNode(g, entry.getValue(), childX, childY, childWidth);
                i++;
            }
        }
    }

    private void drawHighlight(Graphics g, TrieNode node, int x, int y) {
        g.setColor(node.isEndOfWord() ? HIGHLIGHT_COLOR : Color.BLACK);
        g.drawString(String.valueOf(node.getCharacter()), x - 5, y + 5);
    }
}
