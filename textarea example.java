import javax.swing.*;
import java.awt.event.*;

public class TextAreaExample implements ActionListener {
    JTextField input;
    JTextArea output;
    JButton showBtn;

    public TextAreaExample() {
        JFrame f = new JFrame("Output Example");

        input = new JTextField(10);
        showBtn = new JButton("Show");
        output = new JTextArea(5, 20); // 5 rows, 20 columns
        output.setEditable(false);

        showBtn.addActionListener(this); // register listener

        JPanel p = new JPanel();
        p.add(input);
        p.add(showBtn);
        p.add(new JScrollPane(output)); // adds scroll bar

        f.add(p);
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    // handle button click here
    public void actionPerformed(ActionEvent e) {
        String text = input.getText();
        output.setText("You entered: " + text);
    }

    public static void main(String[] args) {
        new TextAreaExample();
    }
}
