import javax.swing.*;
import java.awt.*;

// ✅ Single file beginner-friendly shop app
public class ShopApp {

    // ----------------- Product Class -----------------
    static class Product {
        String sku;
        String name;
        double price;
        double discount; // %
        String phone;

        public Product(String sku, String name, double price, double discount, String phone) {
            this.sku = sku;
            this.name = name;
            this.price = price;
            this.discount = discount;
            this.phone = phone;
        }

        public double getFinalPrice() {
            return price - (price * discount / 100);
        }

        public String toString() {
            return sku + " | " + name + " | ₹" + price + " | disc:" + discount + "% | phone:" + phone;
        }
    }

    // ----------------- Member Class -----------------
    static class Member {
        String id;
        String name;
        int points;

        public Member(String id, String name) {
            this.id = id;
            this.name = name;
            this.points = 0;
        }

        public void addPoints(int p) {
            points += p;
        }

        public String toString() {
            return id + " | " + name + " | points:" + points;
        }
    }

    // ----------------- Simple Generic List -----------------
    static class SimpleList<T> {
        private Object[] data;
        private int size;

        public SimpleList() {
            data = new Object[50];
            size = 0;
        }

        public void add(T item) {
            data[size++] = item;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            return (T) data[index];
        }

        public int size() {
            return size;
        }
    }

    // ----------------- ShopApp Fields -----------------
    SimpleList<Product> products = new SimpleList<>();
    SimpleList<Member> members = new SimpleList<>();

    JTextArea output = new JTextArea(15, 50);

    // ----------------- Constructor -----------------
    public ShopApp() {
        JFrame f = new JFrame("My Shop - Roshan Thomas");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add product form
        JTextField sku = new JTextField(5);
        JTextField name = new JTextField(8);
        JTextField price = new JTextField(5);
        JTextField disc = new JTextField(5);
        JTextField phone = new JTextField(10);
        JButton addBtn = new JButton("Add Product");

        addBtn.addActionListener(e -> {
            try {
                Product p = new Product(
                    sku.getText(),
                    name.getText(),
                    Double.parseDouble(price.getText()),
                    Double.parseDouble(disc.getText()),
                    phone.getText()
                );
                products.add(p);
                showProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid input, please check fields!");
            }
        });

        JPanel top = new JPanel();
        top.add(new JLabel("SKU:")); top.add(sku);
        top.add(new JLabel("Name:")); top.add(name);
        top.add(new JLabel("Price:")); top.add(price);
        top.add(new JLabel("Disc%:")); top.add(disc);
        top.add(new JLabel("Phone:")); top.add(phone);
        top.add(addBtn);

        output.setEditable(false);

        // Buy section
        JTextField buySku = new JTextField(5);
        JTextField qty = new JTextField(5);
        JTextField memberId = new JTextField(5);
        JButton buyBtn = new JButton("Buy");

        buyBtn.addActionListener(e -> {
            String code = buySku.getText();
            String mid = memberId.getText().trim();
            int q = 1;
            try {
                q = Integer.parseInt(qty.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Enter a valid quantity!");
                return;
            }

            boolean found = false;
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                if (p.sku.equals(code)) {
                    found = true;
                    double priceEach = p.getFinalPrice();
                    double total = priceEach * q;

                    boolean isMember = false;
                    Member buyer = null;
                    for (int j = 0; j < members.size(); j++) {
                        if (members.get(j).id.equals(mid)) {
                            isMember = true;
                            buyer = members.get(j);
                            total = total * 0.95; // extra 5% off
                            buyer.addPoints((int)(total / 10));
                            break;
                        }
                    }

                    JOptionPane.showMessageDialog(f,
                        "Customer buys " + q + " x " + p.name +
                        "\nOriginal price: ₹" + p.price +
                        "\nDiscounted price each: ₹" + priceEach +
                        (isMember ? "\nMember extra 5% applied!" : "") +
                        "\nTotal: ₹" + total +
                        (isMember ? "\nPoints earned. Balance: " + buyer.points : ""));
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(f, "Product not found!");
            }
        });

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("SKU:")); bottom.add(buySku);
        bottom.add(new JLabel("Qty:")); bottom.add(qty);
        bottom.add(new JLabel("Member ID:")); bottom.add(memberId);
        bottom.add(buyBtn);

        // Add some sample members
        members.add(new Member("M001", "Roshan"));
        members.add(new Member("M002", "Thomas"));

        // Layout
        f.add(top, BorderLayout.NORTH);
        f.add(new JScrollPane(output), BorderLayout.CENTER);
        f.add(bottom, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
    }

    // ----------------- Helper: show products -----------------
    void showProducts() {
        output.setText("");
        for (int i = 0; i < products.size(); i++) {
            output.append(products.get(i).toString() + "\n");
        }
    }

    // ----------------- Main -----------------
    public static void main(String[] args) {
        new ShopApp();
    }
}
