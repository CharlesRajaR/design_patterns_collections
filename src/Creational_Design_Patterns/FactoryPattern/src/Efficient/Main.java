package Creational_Design_Patterns.FactoryPattern.src.Efficient;

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



