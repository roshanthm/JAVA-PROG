public class SwitchExample {
    public static void main(String args[]) {
        String day = "mon";
        String result = "";

        switch (day) {
            case "tue", "wed" -> result = "1am";
            case "mon" -> result = "2am";
            default -> result = "10am";
        }


        System.out.println(result);
    }
}
