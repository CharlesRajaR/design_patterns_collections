package Efficient;

class Car implements Vehicle{

    @Override
    public void start() {
        System.out.println("car is starting");
    }

    @Override
    public void stop() {
        System.out.println("car is stopped");
    }
}

class Bus implements Vehicle{

    @Override
    public void start() {
        System.out.println("Bus is started");
    }

    @Override
    public void stop() {
        System.out.println("Bus is stopped");
    }
}

class Truck implements Vehicle{

    @Override
    public void start() {
        System.out.println("truck is started");
    }

    @Override
    public void stop() {
        System.out.println("truck is stopped");
    }
}
public class Factory {
    public static Vehicle getVehicle(String name) throws Exception {
        if(name.equals("car")){
            return new Car();
        } else if (name.equals("bus")) {
            return new Bus();
        } else if (name.equals("truck")) {
            return new Truck();
        }
        else {
            throw new Exception("no vehicle found in this name");
        }
    }
}
