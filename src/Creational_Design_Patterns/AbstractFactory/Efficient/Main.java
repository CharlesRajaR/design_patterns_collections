package Creational_Design_Patterns.AbstractFactory.Efficient;

public class Main {
    public static void main(String[] args) {
       VehicleFactory hondaFactory = new HondaFactory();
       Vehicle honda = hondaFactory.createVehicle();
       honda.start();
       honda.stop();
       VehicleFactory bmwFactory = new BMWFactory();
       Vehicle bmw = bmwFactory.createVehicle();
       bmw.start();
       bmw.stop();
    }
}

interface Vehicle{
    void start();
    void stop();
}

class Honda implements Vehicle{

    @Override
    public void start() {
        System.out.println("honda is started");
    }

    @Override
    public void stop() {
        System.out.println("honda is stopped");
    }
}

class BMW implements Vehicle{

    @Override
    public void start() {
        System.out.println("bmw is started");
    }

    @Override
    public void stop() {
        System.out.println("BMW is stopped");
    }
}

class Toyota implements Vehicle{

    @Override
    public void start() {
        System.out.println("toyota is started");
    }

    @Override
    public void stop() {
        System.out.println("toyota is stopped");
    }
}

interface VehicleFactory{
    Vehicle createVehicle();
}

class HondaFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Honda();
    }
}
class BMWFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new BMW();
    }
}

class ToyotaFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Toyota();
    }
}

