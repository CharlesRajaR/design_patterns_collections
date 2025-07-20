import Efficient.Factory;
import Efficient.Vehicle;
import Traditional.CarFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        ===================TRADITIONAL WAY===============
//        CarFactory factory = new CarFactory();
//
//        while (true) {
//            System.out.println("Enter the car you want to create\n 1.car 2.bus 3.truck or type exit");
//            String name = br.readLine();
//            if (name.equals("exit")) {
//                System.exit(0);
//            }
//            else{
//                CarFactory.Vehicle vehicle =  factory.createVehicle(name);
//                vehicle.start();
//                vehicle.stop();
//            }
//        }
        //==============================================================

        //==================ACTUAL WAY====================

        while(true){
            System.out.println("Enter the name of the vehicle you want to " +
                    "create\n1.car \n2.bus \n3.truck \n or type exit");
            String name = br.readLine();
            if(name.equals("exit")){
                System.exit(0);
            }
            else{
                Vehicle vehicle = Factory.getVehicle(name);
                vehicle.start();
                vehicle.stop();
            }
        }


    }
}