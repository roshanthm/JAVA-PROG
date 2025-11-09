
interface Animal {
    void sound();   // abstract method
}

//  Implement the interface in a class
class Dog implements Animal {
    public void sound() {
        System.out.println("The dog barks.");
    }
}


public class InterfaceExample {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.sound();
    }
}
