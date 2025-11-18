package Creational_Design_Patterns.FactoryPattern.src.Efficient;


//The Factory Method pattern defines an interface for creating an object, but delegates the instantiation logic to
//subclasses. It allows a superclass (Creator) to defer the choice of the concrete class
//to be created (Product) to its subclasses. This decouples the client code from the specific product class it uses.

public class Main {
    public static void main(String[] args) {
        Logistics logistics;

        logistics = new TruckLogistics();
        logistics.planDeliver();

        logistics = new ShipLogistics();
        logistics.planDeliver();
    }
}

interface Transport{
    void deliver();
}

class ShipTransport implements Transport{
    @Override
    public void deliver() {
        System.out.println("Delivered by Ship");
    }
}

class TruckTransport implements Transport{

    @Override
    public void deliver() {
        System.out.println("Delivered by Truck");
    }
}

abstract class Logistics{
    public abstract Transport createTransport();

    public void planDeliver(){
        Transport transport = createTransport();
        transport.deliver();
    }
}

class ShipLogistics extends Logistics{
    public Transport createTransport() {
        return new ShipTransport();
    }
}

class TruckLogistics extends Logistics{

    @Override
    public Transport createTransport() {
        return new TruckTransport();
    }
}



