import java.util.Scanner;

class Vehicle {
    String brand;
    String model;
    int year;
    double baseprice;

    Vehicle() {}

    Vehicle(String brand, String model, int year, double baseprice) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.baseprice = baseprice;
    }

    void displayinfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Base Price: " + baseprice);
    }

    void calculateResaleValue() {
        double resaleValue = baseprice * 0.85;
        System.out.println("Resale Value: " + resaleValue);
    }
}

class Car extends Vehicle {
    int numberOfDoors;

    Car(String brand, String model, int year, double baseprice, int numberOfDoors) {
        super(brand, model, year, baseprice);
        this.numberOfDoors = numberOfDoors;
    }

    public void calculateinsurance() {
        double insurance = 1000 + (numberOfDoors * 1000);
        System.out.println("Insurance Cost: " + insurance);
    }

    public void calculatemaintenance() {
        double maintenanceCost = 3000 + (numberOfDoors * 500);
        System.out.println("Maintenance Cost: " + maintenanceCost);
    }

    public void displayinfo() {
        super.displayinfo();
        System.out.println("Number of Doors: " + numberOfDoors);
        calculateinsurance();
        calculatemaintenance();
    }
}

class Motorcycle extends Vehicle {
    String sidecar;

    Motorcycle(String brand, String model, int year, double baseprice, String sidecar) {
        super(brand, model, year, baseprice);
        this.sidecar = sidecar;
    }

    void calculateinsurance() {
        double insurance = 2500;
        if (sidecar.equalsIgnoreCase("Yes")) {
            insurance += 1500;
        }
        System.out.println("Insurance Cost: " + insurance);
    }

    void calculateMaintenance() {
        double maintenanceCost = 2000;
        if (sidecar.equalsIgnoreCase("Yes")) {
            maintenanceCost += 1000;
        }
        System.out.println("Maintenance Cost: " + maintenanceCost);
    }

    
    public void displayinfo() {
        super.displayinfo();
        System.out.println("Sidecar: " + sidecar);
        calculateinsurance();
        calculateMaintenance();
    }
}

public class VehicleManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter vehicle brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = sc.nextLine();

        System.out.print("Enter vehicle year: ");
        int year = sc.nextInt();

        System.out.print("Enter vehicle base price: ");
        double baseprice = sc.nextDouble();

        sc.nextLine(); 

        Vehicle vehicle = new Vehicle(brand, model, year, baseprice);
        System.out.println("\n--- Vehicle Info ---");
        vehicle.displayinfo();
        vehicle.calculateResaleValue();

        Car car = new Car(brand, model, year, baseprice, 4);
        System.out.println("\n--- Car Info ---");
        car.displayinfo();

        System.out.print("\nDoes the motorcycle have a sidecar? (Yes/No): ");
        String sidecar = sc.nextLine();

        Motorcycle motorcycle = new Motorcycle(brand, model, year, baseprice, sidecar);
        System.out.println("\n--- Motorcycle Info ---");
        motorcycle.displayinfo();
    }
}
