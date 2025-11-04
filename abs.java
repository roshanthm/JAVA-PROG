abstract class google{
    abstract void search();
    
    void message(){
        System.out.println("main class abstract");
    }
}

class searchall extends google{
    void search(){
        super.message();
        System.out.println("child class search all");
    }
}

class text extends google{
    void search(){
        System.out.println("text back");
    }
}
public class abs {
    public static void main(String[] args) {
        searchall s = new searchall();
        text t = new text();
        s.search();
        s.message();
        t.search();
        t.message();
    }
}
