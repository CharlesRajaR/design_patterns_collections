package Creational_Design_Patterns.AbstractFactory.Traditional;

public class Main {
    public static void main(String[] args) {
        Vehicle honda = Factory.createVehicle("honda");
        honda.start();
        honda.stop();
        Vehicle toyota = Factory.createVehicle("toyota");
        toyota.start();
        toyota.stop();
        Vehicle maruti = Factory.createVehicle("maruti");
        maruti.start();
        maruti.stop();
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

class Maruti implements Vehicle{

    @Override
    public void start() {
        System.out.println("Maruti is started");
    }

    @Override
    public void stop() {
        System.out.println("Maruti is stopped");
    }
}

class Factory{
    public static Vehicle createVehicle(String name){
        if(name.equals("honda")){
            return new Honda();
        } else if (name.equals("toyota")) {
            return new Toyota();
        } else if (name.equals("maruti")) {
            return new Maruti();
        }else{
            throw new IllegalArgumentException("please provide a valid name");
        }
    }
}