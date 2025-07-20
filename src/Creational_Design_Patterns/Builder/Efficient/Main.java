package Creational_Design_Patterns.Builder.Efficient;

public class Main {
    public static void main(String[] args) {
        VehicleBuilder builder = new VehicleBuilder();
        Vehicle vehicle1 = builder.setName("car").setColor("black")
                .setSeats(6)
                .setWheels(4)
                .build();
        System.out.println(vehicle1.toString());
        VehicleBuilder builder1 = new VehicleBuilder();
        Vehicle vehicle2 = builder1.setName("bus").setColor("yellow").setSeats(50)
                .setWheels(6).build();
        System.out.println(vehicle2.toString());
        VehicleBuilder builder2 = new VehicleBuilder();
        Vehicle vehicle3 = builder2.setName("car").build();
        System.out.println(vehicle3.toString());
    }
}

class VehicleBuilder{
    String name = "";
    int wheels = 4;
    String color = "";
    int seats = 4;
    boolean hasSunRoof = false;
    boolean hasNavigationSystem = false;

    public VehicleBuilder setWheels(int wheels) {
        this.wheels = wheels;
        return this;
    }
    public VehicleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public VehicleBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public VehicleBuilder setSeats(int seats) {
        this.seats = seats;
        return this;
    }

    public VehicleBuilder setHasSunRoof(boolean hasSunRoof) {
        this.hasSunRoof = hasSunRoof;
        return this;
    }

    public VehicleBuilder setHasNavigationSystem(boolean hasNavigationSystem) {
        this.hasNavigationSystem = hasNavigationSystem;
        return this;
    }
    public Vehicle build(){
        return new Vehicle(this);
    }

}

class Vehicle{
    private String name;
    private int wheels;
    private String color;
    private int seats;
    private boolean hasSunRoof;
    private boolean hasNavigationSystem;

    Vehicle(VehicleBuilder builder){
        name = builder.name;
        wheels = builder.wheels;
        color = builder.color;
        seats = builder.seats;
        hasSunRoof = builder.hasSunRoof;
        hasNavigationSystem = builder.hasNavigationSystem;
    }
    public String toString(){
        return "Vehicle name: "+name +"\nNo of wheels present: "+wheels+"" +
                "\nColor: "+ color+"\nseats: "+seats+"\nhasSunRoof: "+hasSunRoof+"" +
                "\nhasNavigationSystem: "+hasNavigationSystem+"\n";
    }
}


