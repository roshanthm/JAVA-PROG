import javax.swing.*;
import java.awt.*;

// Product class
class Product {
    String sku, name;
    double price, discount;
    int stock;

    Product(String sku, String name, double price, double discount, int stock) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
    }

    double getFinalPrice() {
        return price - (price * discount / 100);
    }

    @Override
    public String toString() {
        return sku + " | " + name + " | Price: " + price + " | Disc: " + discount +
                "% | Stock: " + stock;
    }
}

// Simple dynamic array (instead of ArrayList)
class MyArray {
    private Product[] arr = new Product[10];
    private int size = 0;

    void add(Product p) {
        if (size == arr.length) {
            Product[] newArr = new Product[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size++] = p;
    }

    Product get(int i) {
        return arr[i];
    }

    void remove(int index) {
        for (int i = index; i < size - 1; i++) arr[i] = arr[i + 1];
        size--;
    }

    int size() {
        return size;
    }
}

// Second DS: Simple Queue for recently sold products
class MyQueue {
    private Product[] arr = new Product[10];
    private int front = 0, rear = 0, size = 0;

    void enqueue(Product p) {
        if (size == arr.length) {
            Product[] newArr = new Product[arr.length * 2];
            for (int i = 0; i < size; i++)
                newArr[i] = arr[(front + i) % arr.length];
            arr = newArr;
            front = 0;
            rear = size;
        }
        arr[rear] = p;
        rear = (rear + 1) % arr.length;
        size++;
    }

    Product dequeue() {
        if (size == 0) return null;
        Product p = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return p;
    }

    boolean isEmpty() { return size == 0; }
}

public class Shopbill { // class name matches file name
    static MyArray products = new MyArray();
    static MyQueue recentSales = new MyQueue(); // second DS
    static JTextArea output;
    static String billHistory = "";
    static double totalSales = 0.0;
    static final double TAX_RATE = 0.05; // 5% tax

    public static void main(String[] args) {
        JFrame f = new JFrame("🛒 Billing and Inventory Management System");
        f.setSize(800, 600);
        f.setLayout(new BorderLayout());

        // Output area
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));
        f.add(new JScrollPane(output), BorderLayout.CENTER);

        // Panels
        JPanel addPanel = new JPanel(new GridLayout(6, 2));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Product"));

        JTextField sku = new JTextField();
        JTextField name = new JTextField();
        JTextField price = new JTextField();
        JTextField disc = new JTextField();
        JTextField stock = new JTextField();

        addPanel.add(new JLabel("SKU:"));
        addPanel.add(sku);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(name);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(price);
        addPanel.add(new JLabel("Discount %:"));
        addPanel.add(disc);
        addPanel.add(new JLabel("Stock:"));
        addPanel.add(stock);

        JButton addBtn = new JButton("Add Product");
        addPanel.add(addBtn);

        // Buy section
        JPanel buyPanel = new JPanel(new GridLayout(7, 2));
        buyPanel.setBorder(BorderFactory.createTitledBorder("Buy Product"));

        JTextField buySku = new JTextField();
        JTextField qty = new JTextField();
        JTextField phone = new JTextField();
        JCheckBox isMember = new JCheckBox("Is Member?");
        JButton buyBtn = new JButton("Buy");
        JButton historyBtn = new JButton("Show All Bills");
        JButton exportBtn = new JButton("Export Bills");

        buyPanel.add(new JLabel("Enter SKU:"));
        buyPanel.add(buySku);
        buyPanel.add(new JLabel("Quantity:"));
        buyPanel.add(qty);
        buyPanel.add(new JLabel("Phone (if not member):"));
        buyPanel.add(phone);
        buyPanel.add(isMember);
        buyPanel.add(buyBtn);
        buyPanel.add(historyBtn);
        buyPanel.add(exportBtn);

        JPanel south = new JPanel(new GridLayout(1, 2));
        south.add(addPanel);
        south.add(buyPanel);

        f.add(south, BorderLayout.SOUTH);

        // Add Product Action
        addBtn.addActionListener(e -> {
            try {
                String newSku = sku.getText();
                String newName = name.getText();
                double newPrice = Double.parseDouble(price.getText());
                double newDisc = Double.parseDouble(disc.getText());
                int newStock = Integer.parseInt(stock.getText());

                // Check for existing SKU
                for (int i = 0; i < products.size(); i++) {
                    Product existing = products.get(i);
                    if (existing.sku.equalsIgnoreCase(newSku)) {
                        if (existing.price != newPrice || existing.discount != newDisc) {
                            JOptionPane.showMessageDialog(f,
                                    "SKU already exists with different price/discount!");
                        } else {
                            JOptionPane.showMessageDialog(f,
                                    "Product already exists in the store!");
                        }
                        sku.setText("");
                        name.setText("");
                        price.setText("");
                        disc.setText("");
                        stock.setText("");
                        return;
                    }
                }

                Product p = new Product(newSku, newName, newPrice, newDisc, newStock);
                products.add(p);
                showProducts();

                sku.setText("");
                name.setText("");
                price.setText("");
                disc.setText("");
                stock.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid input, please check fields!");
            }
        });

        // Buy Product Action
        buyBtn.addActionListener(e -> {
            try {
                String s = buySku.getText();
                int q = Integer.parseInt(qty.getText());
                boolean member = isMember.isSelected();
                String ph = phone.getText();

                if (!member && ph.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Phone number required for non-members!");
                    return;
                }

                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    if (p.sku.equalsIgnoreCase(s)) {
                        if (q > p.stock) {
                            JOptionPane.showMessageDialog(f,
                                    "Not enough stock! Available: " + p.stock);
                            buySku.setText("");
                            qty.setText("");
                            phone.setText("");
                            isMember.setSelected(false);
                            return;
                        }

                        double basePrice = p.getFinalPrice() * q;
                        if (member) basePrice *= 0.9;
                        double tax = basePrice * TAX_RATE;
                        double finalPrice = basePrice + tax;

                        p.stock -= q;
                        if (p.stock == 0) products.remove(i);

                        recentSales.enqueue(p);

                        String bill = "\n🧾 BILL RECEIPT\n" +
                                "Product: " + p.name + "\n" +
                                "Quantity: " + q + "\n" +
                                "Price per Item (after discount): " + p.getFinalPrice() + "\n" +
                                (member ? "Extra Member Discount: 10%\n" : "") +
                                "Subtotal: " + basePrice + "\n" +
                                "Tax (5%): " + tax + "\n" +
                                "Total Price: " + finalPrice + "\n" +
                                "Customer: " + (member ? "Member" : "Non-member, Phone: " + ph) + "\n" +
                                "===================================\n";

                        billHistory += bill;
                        totalSales += finalPrice;

                        output.append(bill);
                        JOptionPane.showMessageDialog(f, bill, "Bill Receipt", JOptionPane.INFORMATION_MESSAGE);

                        showProducts();

                        buySku.setText("");
                        qty.setText("");
                        phone.setText("");
                        isMember.setSelected(false);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(f, "Product not found!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid input in buy section!");
            }
        });

        // Show All Bills Action
        historyBtn.addActionListener(e -> {
            if (billHistory.isEmpty()) {
                JOptionPane.showMessageDialog(f, "No bills yet!");
            } else {
                JOptionPane.showMessageDialog(f,
                        billHistory + "\n💰 Daily Total Sales: " + totalSales,
                        "Billing History",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Export Bills Action
        exportBtn.addActionListener(e -> {
            if (billHistory.isEmpty()) {
                JOptionPane.showMessageDialog(f, "No bills to export!");
            } else {
                try {
                    java.io.FileWriter writer = new java.io.FileWriter("bills.txt");
                    writer.write("=== Billing History ===\n");
                    writer.write(billHistory);
                    writer.write("\n💰 Daily Total Sales: " + totalSales + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(f, "Bills exported successfully to bills.txt");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Error saving file!");
                }
            }
        });

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void showProducts() {
        output.setText("📦 Store Inventory:\n");
        for (int i = 0; i < products.size(); i++) {
            output.append(products.get(i).toString() + "\n");
        }
        output.append("-----------------------------\n");
    }
}
