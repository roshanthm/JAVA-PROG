import javax.swing.*;
import java.awt.event.*;

public class MyGUI implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton button1, button2;

    public MyGUI() {
        frame = new JFrame("Roshan's GUI");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 30, 300, 30);
        frame.add(textField);

        button1 = new JButton("Say Hello");
        button1.setBounds(50, 80, 120, 40);
        button1.addActionListener(this); // listen for clicks
        frame.add(button1);

        button2 = new JButton("Clear");
        button2.setBounds(200, 80, 120, 40);
        button2.addActionListener(this); // listen for clicks
        frame.add(button2);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            textField.setText("Hello, Roshan!");
        } else if (e.getSource() == button2) {
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new MyGUI();
    }
}
