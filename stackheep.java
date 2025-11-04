class computer{
    int num=10;
    public int add(int n1){
        return n1+n1;
    }
}

public class stackheep {
    public static void main(String[] args) {
        computer obj =new computer();
        computer obj1 = new computer();
        obj.num=5;
        System.out.println(obj.num);
        System.out.println(obj1.num);
    
    }
}
