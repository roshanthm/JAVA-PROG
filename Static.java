public class phello {
    int b=20;
    static int a=10;
    public static void main(String[] args) {
        System.out.println(a);   //10
        hello(10);

        //without static 
        phello h = new phello();
        phello h1 = new phello();
        a=100;                    // can use h.a=100          
        System.out.println(h.a);  //phello.h and a can also print  100
        h.b=200;
        System.err.println(h.b); //200
        h.check(22);           //22


        // diff casess

        h.a=-1;
        h1.a=-2;
        System.out.println(h.a); //-2
        System.out.println(h1.a); //-2
        a=-3;
        System.out.println(h.a); //-3
        System.out.println(h1.a);//-3


        //2nd case

        h.b=-101;
        h1.b=-102;
         System.out.println(h.b); //-101
        System.out.println(h1.b);//-102
        


    





    }
    static void hello(int b){
        System.out.println(b);
    }
    void check(int c){
        System.out.println(c);
    }
}

