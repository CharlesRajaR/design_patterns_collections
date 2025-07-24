package Structural_Design_Patterns.AdapterDesignPattern;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Adapter Design Pattern Implementation");
        AirConditioner AC = new AirConditioner();
        SmartLight light = new SmartLight();
        AirConditionerAdapter aca = new AirConditionerAdapter(AC);
        SmartLightAdapter sla = new SmartLightAdapter(light);
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Select any one option\n1.on ac\n2.off ac\n3.on light\n4.light\n5.exit");
            switch (sc.nextInt()){
                case 1:
                    aca.turnOn();
                    break;
                case 2:
                    aca.turnOff();
                    break;
                case 3:
                    light.on();
                    break;
                case 4:
                    light.off();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
interface SmartDevice{
    void turnOn() throws InterruptedException;
    void turnOff();
}

class AirConditioner{
    private boolean isConnect = false;
    void connect() throws InterruptedException {
        System.out.println("connecting air conditioner via bluetooth...");
        Thread.sleep(1000);
        isConnect = true;
        System.out.println("air conditioner connected to bluetooth successfully");
    }

    void startCooling(){
        System.out.println("ac started producing cool air");
    }

    void stopCooling(){
        System.out.println("ac stop producing cool air");
    }

    void disconnect(){
        System.out.println("air conditioner disconnected");
        isConnect = false;
    }

    public boolean isConnected(){
        return isConnect;
    }
}

class AirConditionerAdapter implements SmartDevice{
    private AirConditioner airConditioner;

    AirConditionerAdapter(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }
    @Override
    public void turnOn() throws InterruptedException {
        if(!airConditioner.isConnected()){
            airConditioner.connect();
        }
        airConditioner.startCooling();
    }

    @Override
    public void turnOff() {
        if(airConditioner.isConnected()){
            airConditioner.stopCooling();
            airConditioner.disconnect();
        }
    }
}

class SmartLight{
    private boolean isConnect = false;
    void connect() throws InterruptedException {
        System.out.println("connecting light to wifi...");
        Thread.sleep(1000);
        System.out.println("light connected to wifi successfully");
        isConnect = true;
    }

    void on(){
        System.out.println("light is on");
    }

    void off(){
        System.out.println("light is off");
    }

    void disconnect(){
        System.out.println("light is disconnected");
        isConnect = false;
    }

    public boolean isConnected(){
        return isConnect;
    }
}

class SmartLightAdapter implements SmartDevice{
    private SmartLight smartLight;

    SmartLightAdapter(SmartLight smartLight){
        this.smartLight = smartLight;
    }
    @Override
    public void turnOn() throws InterruptedException {
        if(!smartLight.isConnected()){
            smartLight.connect();
            smartLight.on();
        }
    }

    @Override
    public void turnOff() {
        if(smartLight.isConnected()){
            smartLight.off();
            smartLight.disconnect();
        }
    }
}