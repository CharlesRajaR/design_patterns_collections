package Creational_Design_Patterns.Builder.Traditional;

public class Main {
    public static void main(String[] args) {
         Vehicle vehicle = new Vehicle("car", 4, "blue", 4,  false, false);
         //if some value will take default then we create another constructor
        System.out.println(vehicle.toString());
    }

}

class Vehicle{
    private String name;
    private int wheels;
    private String color;
    private int seats;
    private boolean hasSunRoof;
    private boolean hasNavigationSystem;

    public Vehicle(String name, int wheels, String color, int seats, boolean hasSunRoof, boolean hasNavigationSystem){
        this.name = name;
        this.wheels = wheels;
        this.color = color;
        this.seats = seats;
        this.hasSunRoof = hasSunRoof;
        this.hasNavigationSystem = hasNavigationSystem;
    }

    public String toString(){
        return "Vehicle name: "+name +"\nNo of wheels present: "+wheels+"" +
                "\nColor: "+ color+"\nseats: "+seats+"\nhasSunRoof: "+hasSunRoof+"\nhasNavigationSystem: "+hasNavigationSystem;
    }
}

