interface Animal {
    void sound();
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }

    public void eat() {
        System.out.println("Dog eats bone");
    }
}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.sound();  // ✅ allowed
        d.eat();    // ✅ allowed (Dog-specific method)

        Animal a = new Dog();
        a.sound();  // ✅ allowed (method declared in Animal)
        // a.eat(); // ❌ error: cannot find symbol (not in Animal)
    }
}
