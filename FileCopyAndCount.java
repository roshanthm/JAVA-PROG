import java.io.*;
import java.util.*;

public class FileCopyAndCount {
    public static void main(String[] args) {
        try {
            File inputFile = new File("sample.txt");
            File outputFile = new File("copy.txt");

            // Readers and Writers
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(outputFile);

            int charCount = 0, lineCount = 0;
            String line;

            // --- Count words using Scanner ---
            Scanner sc = new Scanner(inputFile);
            int wordCount = 0;
            while (sc.hasNext()) {  // Each next() = one word
                sc.next();
                wordCount++;
            }
            sc.close();

            // --- Copy file and count lines/characters ---
            while ((line = br.readLine()) != null) {
                fw.write(line + "\n");
                lineCount++;
                charCount += line.length(); // includes spaces
            }

            br.close();
            fw.close();

            // --- Display results ---
            System.out.println("File copied successfully!");
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters (including spaces): " + charCount);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
