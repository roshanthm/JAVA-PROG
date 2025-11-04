import javax.swing.*;
import java.awt.event.*;

public class MyGUI implements ActionListener {
    JFrame frame;
    JTextField text;
    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton equalButton, clearButton, deleteButton;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    public MyGUI() {
        frame = new JFrame("Roshan's Calculator");
        frame.setSize(340, 460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Display field
        text = new JTextField();
        text.setBounds(30, 30, 260, 40);
        frame.add(text);

        // Create number buttons 0–9
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            frame.add(numberButtons[i]);
        }

        // Layout: 1 2 3 +
        numberButtons[1].setBounds(30, 90, 60, 40);
        numberButtons[2].setBounds(100, 90, 60, 40);
        numberButtons[3].setBounds(170, 90, 60, 40);
        addButton = new JButton("+");
        addButton.setBounds(240, 90, 60, 40);

        // Layout: 4 5 6 -
        numberButtons[4].setBounds(30, 140, 60, 40);
        numberButtons[5].setBounds(100, 140, 60, 40);
        numberButtons[6].setBounds(170, 140, 60, 40);
        subButton = new JButton("-");
        subButton.setBounds(240, 140, 60, 40);

        // Layout: 7 8 9 *
        numberButtons[7].setBounds(30, 190, 60, 40);
        numberButtons[8].setBounds(100, 190, 60, 40);
        numberButtons[9].setBounds(170, 190, 60, 40);
        mulButton = new JButton("*");
        mulButton.setBounds(240, 190, 60, 40);

        // Layout: 0 C Del =
        numberButtons[0].setBounds(30, 240, 60, 40);
        clearButton = new JButton("C");
        clearButton.setBounds(100, 240, 60, 40);
        deleteButton = new JButton("Del");
        deleteButton.setBounds(170, 240, 60, 40);
        equalButton = new JButton("=");
        equalButton.setBounds(240, 240, 60, 40);

        // Layout: / at bottom
        divButton = new JButton("/");
        divButton.setBounds(30, 290, 270, 40);

        // Add listeners
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equalButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);

        // Add operation buttons
        frame.add(addButton);
        frame.add(subButton);
        frame.add(mulButton);
        frame.add(divButton);
        frame.add(equalButton);
        frame.add(clearButton);
        frame.add(deleteButton);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle digits
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                text.setText(text.getText() + i);
            }
        }

        // Handle operations
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(text.getText());
            operator = "+";
            text.setText("");
        } else if (e.getSource() == subButton) {
            num1 = Double.parseDouble(text.getText());
            operator = "-";
            text.setText("");
        } else if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(text.getText());
            operator = "*";
            text.setText("");
        } else if (e.getSource() == divButton) {
            num1 = Double.parseDouble(text.getText());
            operator = "/";
            text.setText("");
        } else if (e.getSource() == equalButton) {
            num2 = Double.parseDouble(text.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num2 != 0 ? num1 / num2 : 0; break;
            }
            text.setText(String.valueOf(result));
        } else if (e.getSource() == clearButton) {
            text.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (e.getSource() == deleteButton) {
            String current = text.getText();
            if (!current.isEmpty()) {
                text.setText(current.substring(0, current.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new MyGUI();
    }
}
