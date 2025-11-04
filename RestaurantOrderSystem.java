import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// -------- MenuItem Class --------
class MenuItem {
    String name;
    int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

// -------- Stack for Orders --------
class OrderStack {
    private String[] orders;
    private int top;

    public OrderStack(int capacity) {
        orders = new String[capacity];
        top = -1;
    }

    public void push(String order) {
        if (top + 1 == orders.length) {
            // expand array if full
            String[] newArr = new String[orders.length * 2];
            for (int i = 0; i <= top; i++) newArr[i] = orders[i];
            orders = newArr;
        }
        orders[++top] = order;
    }

    public String pop() throws Exception {
        if (top == -1) throw new Exception("No orders to undo!");
        return orders[top--];
    }

    public boolean isEmpty() { return top == -1; }
}

// -------- Main GUI Class --------
public class RestaurantOrderSystem extends JFrame implements  ActionListener {
    MenuItem[] menuItems;
    JCheckBox[] checkboxes;
    JLabel totalLabel;
    JButton orderButton, clearButton, sortButton, undoButton;
    OrderStack orderHistory;
    JPanel panel;

    public RestaurantOrderSystem() {
        setTitle("🍴 Restaurant Order System");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize menu
        menuItems = new MenuItem[6];
        menuItems[0] = new MenuItem("Burger", 120);
        menuItems[1] = new MenuItem("Pizza", 250);
        menuItems[2] = new MenuItem("Pasta", 180);
        menuItems[3] = new MenuItem("Biryani", 300);
        menuItems[4] = new MenuItem("Fried Rice", 150);
        menuItems[5] = new MenuItem("Ice Cream", 80);

        // Initialize order history stack
        orderHistory = new OrderStack(5);

        // GUI
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 8, 8));
        panel.setBackground(new Color(245, 250, 240));

        JLabel title = new JLabel("Welcome to Our Restaurant!", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(new Color(0, 102, 102));
        panel.add(title);

        createMenuCheckboxes();

        totalLabel = new JLabel("Total: Rs. 0", SwingConstants.CENTER);
        panel.add(totalLabel);

        orderButton = new JButton("Place Order");
        clearButton = new JButton("Clear Order");
        sortButton = new JButton("Sort by Price");
        undoButton = new JButton("Undo Last Order");

        orderButton.addActionListener(this);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { clearSelection(); }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { sortMenuByPrice(); }
        });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { undoLastOrder(); }
        });

        panel.add(orderButton);
        panel.add(clearButton);
        panel.add(sortButton);
        panel.add(undoButton);

        add(panel);
        setVisible(true);
    }

    private void createMenuCheckboxes() {
        checkboxes = new JCheckBox[menuItems.length];
        for (int i = 0; i < menuItems.length; i++) {
            checkboxes[i] = new JCheckBox(menuItems[i].name + " - Rs. " + menuItems[i].price);
                        panel.add(checkboxes[i]);
        }
    }

    // -------- Place Order --------
    public void actionPerformed(ActionEvent e) {
        try {
            int total = 0;
            String orderDetails = "Items Ordered:\n";
            boolean selected = false;

            // Traverse all checkboxes (traversal algorithm)
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    selected = true;
                    orderDetails += "• " + menuItems[i].name + " - Rs. " + menuItems[i].price + "\n";
                    total += menuItems[i].price;
                }
            }

            if (!selected) throw new Exception("Please select at least one item!");

            totalLabel.setText("Total: Rs. " + total);
            orderHistory.push(orderDetails);

            JOptionPane.showMessageDialog(this, orderDetails + "\nTotal Amount: Rs. " + total,
                    "Order Placed Successfully", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearSelection() {
        for (JCheckBox cb : checkboxes) cb.setSelected(false);
        totalLabel.setText("Total: Rs. 0");
    }

    // -------- Bubble Sort Algorithm --------
    private void sortMenuByPrice() {
        for (int i = 0; i < menuItems.length - 1; i++) {
            for (int j = 0; j < menuItems.length - i - 1; j++) {
                if (menuItems[j].price > menuItems[j + 1].price) {
                    MenuItem temp = menuItems[j];
                    menuItems[j] = menuItems[j + 1];
                    menuItems[j + 1] = temp;
                }
            }
        }
        rebuildUI("Menu Sorted by Price");
    }

    private void rebuildUI(String titleText) {
        panel.removeAll();
        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        
        panel.add(title);
        createMenuCheckboxes();
        panel.add(totalLabel);
        panel.add(orderButton);
        panel.add(clearButton);
        panel.add(sortButton);
        panel.add(undoButton);
        panel.revalidate();
        panel.repaint();
    }

    private void undoLastOrder() {
        try {
            String lastOrder = orderHistory.pop();
            JOptionPane.showMessageDialog(this, "Last order removed:\n" + lastOrder,
                    "Undo Order", JOptionPane.INFORMATION_MESSAGE);
            clearSelection();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Undo Error", JOptionPane.WARNING_MESSAGE);
        }
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new RestaurantOrderSystem();
        }
    });
}
}