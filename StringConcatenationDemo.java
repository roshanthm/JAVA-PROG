public class StringConcatenationDemo {
    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "World";

        // 1. Using concat()
        String result1 = s1.concat(" ").concat(s2);
        System.out.println("Using concat(): " + result1);

        // 2. Using + operator
        String result2 = s1 + " " + s2;
        System.out.println("Using + operator: " + result2);

        // 3. Using String.join()
        String result3 = String.join(" ", s1, s2);
        System.out.println("Using String.join(): " + result3);

        // 4. Converting numbers to strings using valueOf()
        int num = 123;
        String numString = String.valueOf(num);
        System.out.println("Number converted to String: " + numString);

        // Using valueOf() inside concatenation
        String combined = "Value is: " + String.valueOf(num);
        System.out.println(combined);
    }
}
