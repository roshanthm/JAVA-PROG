import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Snake extends JFrame {
    private static final int SIZE = 10;
    private static final int WIN_POINT = 100;
    private static final Map<Integer, Integer> snakes = new HashMap<>();
    private static final Map<Integer, Integer> ladders = new HashMap<>();

    private final JButton rollButton = new JButton("🎲 Roll Dice");
    private final JLabel statusLabel = new JLabel("Player 1's turn", SwingConstants.CENTER);
    private final JLabel diceLabel = new JLabel("Roll: ", SwingConstants.CENTER);
    private final BoardPanel boardPanel = new BoardPanel();

    private int player1Pos = 1, player2Pos = 1;
    private boolean player1Turn = true;
    private final Random rand = new Random();
    private int rollingValue = 1;
    private boolean rolling = false;

    public Snake() {
        super("Snake & Ladder — Pro Edition");
        initializeBoardData();

        setLayout(new BorderLayout(10, 10));
        add(boardPanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);

        setSize(750, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel control = new JPanel(new GridLayout(2, 1, 5, 5));
        control.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        diceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel top = new JPanel();
        top.add(statusLabel);
        top.add(diceLabel);

        rollButton.setFont(new Font("Arial", Font.BOLD, 18));
        rollButton.addActionListener(e -> animateDiceRoll());
        JPanel bottom = new JPanel();
        bottom.add(rollButton);

        control.add(top);
        control.add(bottom);
        return control;
    }

    // Smooth dice animation
    private void animateDiceRoll() {
        if (rolling) return;
        rolling = true;
        final int[] count = {0};
        final javax.swing.Timer timer = new javax.swing.Timer(100, null);
        timer.addActionListener(e -> {
            count[0]++;
            rollingValue = rand.nextInt(6) + 1;
            diceLabel.setText("Rolling: " + rollingValue);
            if (count[0] > 8) {
                timer.stop();
                rolling = false;
                rollDiceAction(rollingValue);
            }
        });
        timer.start();
    }

    private void rollDiceAction(int dice) {
        diceLabel.setText("Roll: " + dice);
        if (player1Turn) {
            player1Pos = movePlayer(player1Pos, dice);
            boardPanel.repaint();
            if (player1Pos >= WIN_POINT) { endGame("Player 1"); return; }
        } else {
            player2Pos = movePlayer(player2Pos, dice);
            boardPanel.repaint();
            if (player2Pos >= WIN_POINT) { endGame("Player 2"); return; }
        }

        player1Turn = !player1Turn;
        statusLabel.setText((player1Turn ? "Player 1" : "Player 2") + "'s turn");
    }

    private int movePlayer(int position, int dice) {
        int next = position + dice;
        if (next > WIN_POINT) return position;

        // Only climb or slide if you *land exactly* on a cell
        if (ladders.containsKey(next)) {
            JOptionPane.showMessageDialog(this, "🪜 Ladder! Climb to " + ladders.get(next));
            next = ladders.get(next);
        } else if (snakes.containsKey(next)) {
            JOptionPane.showMessageDialog(this, "🐍 Snake bite! Down to " + snakes.get(next));
            next = snakes.get(next);
        }
        return next;
    }

    private void endGame(String winner) {
        JOptionPane.showMessageDialog(this, "🏆 " + winner + " wins!");
        rollButton.setEnabled(false);
        statusLabel.setText("Game Over");
    }

    private void initializeBoardData() {
        ladders.put(3, 22);
        ladders.put(5, 8);
        ladders.put(11, 26);
        ladders.put(20, 29);
        ladders.put(27, 56);
        ladders.put(40, 59);
        ladders.put(70, 90);

        snakes.put(17, 4);
        snakes.put(19, 7);
        snakes.put(54, 34);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(99, 78);
    }

    class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int w = getWidth() / SIZE;
            int h = getHeight() / SIZE;
            int num = 1;
            boolean leftToRight = true;

            // Draw board cells (start from bottom-left = 1)
            for (int row = SIZE - 1; row >= 0; row--) {
                for (int col = 0; col < SIZE; col++) {
                    int c = leftToRight ? col : (SIZE - 1 - col);
                    int x = c * w;
                    int y = row * h;
                    g.setColor((row + col) % 2 == 0 ? new Color(255, 255, 240) : new Color(240, 255, 255));
                    g.fillRect(x, y, w, h);
                    g.setColor(Color.gray);
                    g.drawRect(x, y, w, h);
                    g.setColor(Color.black);
                    g.drawString(String.valueOf(num++), x + 5, y + 15);
                }
                leftToRight = !leftToRight;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            // Draw ladders 🪜
            g2.setColor(new Color(65, 105, 225));
            for (var e : ladders.entrySet()) drawLadder(g2, e.getKey(), e.getValue(), w, h);

            // Draw snakes 🐍
            g2.setColor(new Color(178, 34, 34));
            for (var e : snakes.entrySet()) drawSnake(g2, e.getKey(), e.getValue(), w, h);

            // Draw players
            drawPlayer(g, player1Pos, w, h, Color.CYAN, -10);
            drawPlayer(g, player2Pos, w, h, Color.PINK, +10);
        }

        private void drawSnake(Graphics2D g2, int from, int to, int w, int h) {
            Point p1 = cellCenter(from, w, h);
            Point p2 = cellCenter(to, w, h);
            QuadCurve2D q = new QuadCurve2D.Float(
                    p1.x, p1.y,
                    (p1.x + p2.x) / 2 + 30, (p1.y + p2.y) / 2 - 60,
                    p2.x, p2.y
            );
            g2.draw(q);
            g2.fillOval(p1.x - 6, p1.y - 6, 12, 12); // Snake head
        }

        private void drawLadder(Graphics2D g2, int from, int to, int w, int h) {
            Point p1 = cellCenter(from, w, h);
            Point p2 = cellCenter(to, w, h);
            g2.drawLine(p1.x - 5, p1.y, p2.x - 5, p2.y);
            g2.drawLine(p1.x + 5, p1.y, p2.x + 5, p2.y);
            for (int i = 0; i <= 5; i++) {
                int x1 = p1.x - 5 + (i * (p2.x - p1.x)) / 5;
                int y1 = p1.y + (i * (p2.y - p1.y)) / 5;
                g2.drawLine(x1 - 5, y1, x1 + 5, y1);
            }
        }

        private void drawPlayer(Graphics g, int pos, int w, int h, Color color, int offset) {
            if (pos <= 0) return;
            Point p = cellCenter(pos, w, h);
            g.setColor(color);
            g.fillOval(p.x - 10, p.y - 10 + offset, 20, 20);
            g.setColor(Color.black);
            g.drawOval(p.x - 10, p.y - 10 + offset, 20, 20);
        }

        private Point cellCenter(int position, int w, int h) {
            int index = position - 1;
            int row = index / SIZE;
            int col = (row % 2 == 0) ? (index % SIZE) : (SIZE - 1 - (index % SIZE));
            int x = col * w + w / 2;
            int y = (SIZE - 1 - row) * h + h / 2;
            return new Point(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Snake::new);
    }
}
