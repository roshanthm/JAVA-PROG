import java.io.*;
import java.util.StringTokenizer;


public class AddNumbersFromFile {
    public static void main(String[] args) {
        int sum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split using both space and plus as delimiters
                StringTokenizer st = new StringTokenizer(line, " +");

                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    try {
                        sum += Integer.parseInt(token);
                    } catch (NumberFormatException ignore) {
                        // skip anything that's not a valid integer
                    }
                }
            }

            System.out.println("Sum of numbers = " + sum);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
