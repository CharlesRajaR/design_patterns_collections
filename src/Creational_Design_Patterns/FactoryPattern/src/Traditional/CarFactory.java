package Creational_Design_Patterns.FactoryPattern.src.Traditional;

public class CarFactory {
    public abstract class Vehicle{
        public abstract void start();
        public abstract void stop();
    }

    public class Car extends Vehicle{

        @Override
        public void start() {
            System.out.println("car is starting");
        }

        @Override
        public void stop() {
            System.out.println("car is stopping");
        }
    }

    public class Bus extends Vehicle{

        @Override
        public void start() {
            System.out.println("bus is starting");
        }

        @Override
        public void stop() {
            System.out.println("bus is stopping");
        }
    }

    class Truck extends Vehicle{

        @Override
        public void start() {
            System.out.println("truck is starting");
        }

        @Override
        public void stop() {
            System.out.println("truck is stopping");
        }
    }

    public Vehicle createVehicle(String name) throws Exception {
        if(name.equals("car")){
            return new Car();
        } else if (name.equals("bus")) {
            return new Bus();
        }
        else if(name.equals("truck")){
            return new Truck();
        }
        else{
            throw new Exception("give valid vehicle name");
        }
    }
}
