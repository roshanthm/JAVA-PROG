public class StringConstructorsDemo {
    public static void main(String[] args) {

        // 1. Empty constructor
        String s1 = new String("");  
        System.out.println("Empty: '" + s1 + "'");

        // 2. From existing String
        String s2 = new String("Hello");  
        System.out.println("From String: " + s2);

        // 3. From character array
        char[] chars = {'J', 'a', 'v', 'a'};
        String s3 = new String(chars);  
        System.out.println("From char[]: " + s3);

        // 4. From part of a character array
        String s4 = new String(chars, 1, 3);  
        System.out.println("From part of char[]: " + s4);

        // 5. From byte array
        byte[] bytes = {65, 66, 67};
        String s5 = new String(bytes);  
        System.out.println("From byte[]: " + s5);
    }
}
