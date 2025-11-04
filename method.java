class computer {

    public void runcode(){
        System.out.println("the code is working");
    }

    public String output(int n){
        if (n>3){
            return "less effecient";
        }else{
            return "best ";
        }
    }

    
}


public class method {
    public static void main(String[] args) {
        computer cs =new computer();
        cs.runcode();
        String result = cs.output(3);
        System.out.println(result);
    }
}
