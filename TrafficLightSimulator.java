import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TrafficLightPanel extends JPanel {
    private String currentColor = "RED";

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw outline
        g.setColor(Color.BLACK);
        g.fillRect(100, 50, 100, 300);

        // Draw red light
        g.setColor(currentColor.equals("RED") ? Color.RED : Color.GRAY);
        g.fillOval(125, 75, 50, 50);

        // Draw yellow light
        g.setColor(currentColor.equals("YELLOW") ? Color.YELLOW : Color.GRAY);
        g.fillOval(125, 150, 50, 50);

        // Draw green light
        g.setColor(currentColor.equals("GREEN") ? Color.GREEN : Color.GRAY);
        g.fillOval(125, 225, 50, 50);
    }

    public void changeLight() {
        switch (currentColor) {
            case "RED":
                currentColor = "GREEN";
                break;
            case "GREEN":
                currentColor = "YELLOW";
                break;
            case "YELLOW":
                currentColor = "RED";
                break;
        }
        repaint();
    }
}

public class TrafficLightSimulator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light");
        TrafficLightPanel panel = new TrafficLightPanel();
        JButton button = new JButton("Change Light");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.changeLight();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setSize(300, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
