import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] students = new Student[3];

        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter name and age for student " + (i + 1) + ":");
            String name = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine(); // consume newline

            students[i] = new Student(name, age);
        }

        System.out.println("\nStudent Details:");
        for (Student student : students) {
            student.display();
        }
    }
}

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
