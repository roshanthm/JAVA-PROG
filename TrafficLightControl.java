import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TrafficLightPanel extends JPanel {
    String currentColor = "NONE";

    void setColor(String color) {
        currentColor = color;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(100, 50, 100, 300);

        g.setColor(currentColor.equals("RED") ? Color.RED : Color.GRAY);
        g.fillOval(125, 75, 50, 50);

        g.setColor(currentColor.equals("YELLOW") ? Color.YELLOW : Color.GRAY);
        g.fillOval(125, 150, 50, 50);

        g.setColor(currentColor.equals("GREEN") ? Color.GREEN : Color.GRAY);
        g.fillOval(125, 225, 50, 50);
    }
}

class TrafficLightControl implements ActionListener {
    JFrame frame;
    TrafficLightPanel panel;
    JButton redButton, yellowButton, greenButton;

    TrafficLightControl() {
        frame = new JFrame("Traffic Light Control");
        panel = new TrafficLightPanel();

        redButton = new JButton("Red");
        yellowButton = new JButton("Yellow");
        greenButton = new JButton("Green");

        redButton.addActionListener(this);
        yellowButton.addActionListener(this);
        greenButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(redButton);
        buttonPanel.add(yellowButton);
        buttonPanel.add(greenButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(300, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == redButton) {
            panel.setColor("RED");
        } else if (source == yellowButton) {
            panel.setColor("YELLOW");
        } else if (source == greenButton) {
            panel.setColor("GREEN");
        }
    }

    public static void main(String[] args) {
        new TrafficLightControl();
    }
}
