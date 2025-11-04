import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField input;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        input = new JTextField();
        input.setBounds(30, 20, 220, 40);
        add(input);

        String[] labels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "C", "=", "/"
        };

        JButton[] buttons = new JButton[16];

        int x = 30, y = 80;
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].setBounds(x, y, 50, 50);
            add(buttons[i]);
            buttons[i].addActionListener(this);

            x += 60;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            input.setText(input.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            if (!input.getText().isEmpty()) {
                num1 = Double.parseDouble(input.getText());
                operator = cmd;
                input.setText("");
            }
        } else if (cmd.equals("=")) {
            if (!input.getText().isEmpty()) {
                num2 = Double.parseDouble(input.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 != 0) result = num1 / num2;
                        else {
                            input.setText("Error");
                            return;
                        }
                        break;
                }
                input.setText(String.valueOf(result));
            }
        } else if (cmd.equals("C")) {
            input.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
