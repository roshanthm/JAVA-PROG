class ThrowsExample {
    void checkAge(int age) throws ArithmeticException {
        if (age < 18)
            throw new ArithmeticException("Underage");
        else
            System.out.println("Eligible");
    }

    public static void main(String[] args) {
        ThrowsExample obj = new ThrowsExample();
        try {
            obj.checkAge(15);  // method may throw exception
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
