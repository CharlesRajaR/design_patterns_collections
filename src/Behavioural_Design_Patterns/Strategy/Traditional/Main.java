package Behavioural_Design_Patterns.Strategy.Traditional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("Enter the payment method to pay\n1.stripe\n2.razorpay\n3.gPay\n or type exit");
            String name = br.readLine();
            if(name.equals("exit")){
                break;
            }
            PaymentProcessor processor = new PaymentProcessor();
            processor.makePayment(name);
        }
    }
}

interface PaymentMethod {
    void processPayment();
}
class Stripe implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("payment processed by stripe");
    }
}

class Razorpay implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("payment processed by razorpay");
    }
}

class GPay implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("payment processed by GPay");
    }
}

class PaymentProcessor{
    public void makePayment(String name){
        if(name.equals("stripe")){
            PaymentMethod processor = new Stripe();
            processor.processPayment();
        } else if (name.equals("razorpay")) {
            PaymentMethod processor = new Razorpay();
            processor.processPayment();
        } else if (name.equals("gpay")) {
            PaymentMethod paymentMethod = new GPay();
            paymentMethod.processPayment();
        }else{
            System.out.println("give valid payment method");
        }
    }
}