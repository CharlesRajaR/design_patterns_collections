package Behavioural_Design_Patterns.Strategy.Efficient;

public class Main {
    public static void main(String[] args) {
        PaymentStrategy stripeStrategy = new Stripe();
        PaymentStrategy razorpayStrategy = new Razorpay();
        PaymentStrategy upiStrategy = new UPI();

        PaymentProcessor paymentProcessor = new PaymentProcessor(stripeStrategy);
        paymentProcessor.processPayment();
        paymentProcessor.setPaymentStrategy(razorpayStrategy);
        paymentProcessor.processPayment();
        paymentProcessor.setPaymentStrategy(upiStrategy);
        paymentProcessor.processPayment();
    }
}

interface PaymentStrategy{
    void processPayment();
}

class Stripe implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("payment processed by stripe");
    }
}

class Razorpay implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("payment processed by razorpay");
    }
}

class UPI implements PaymentStrategy{
    @Override
    public void processPayment() {
        System.out.println("payment processed by upi");
    }
}

class PaymentProcessor{
    PaymentStrategy paymentStrategy;

    PaymentProcessor(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(){
        paymentStrategy.processPayment();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
}