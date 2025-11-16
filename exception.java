public class ExceptionDemo {
    public static void main(String[] args) {

        try {
            // NumberFormatException
            int a = Integer.parseInt("abc");   // invalid number

            // ArithmeticException
            int x = 10 / 0;                    // divide by zero

            // ArrayIndexOutOfBoundsException
            int arr[] = {1, 2, 3};
            System.out.println(arr[5]);        // invalid index
        }

        catch (NumberFormatException e) {
            System.out.println("NumberFormatException occurred");
        }

        catch (ArithmeticException e) {
            System.out.println("ArithmeticException occurred");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException occurred");
        }

        catch (Exception e) {
            System.out.println("Some other exception occurred");
        }
    }
}
