class Prime {
    public static void main(String[] args) {
        String s = "ello";
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.println(i + " : " + s.charAt(i));
        }
    }
}
