package Structural_Design_Patterns.DecoratorDesignPattern;

//                      ===================================
//                      |    DECORATOR DESIGN PATTERN     |
//                      ===================================
//   It allows you to add new behaviour or responsibilities to the object dynamically without altering the original code or class structure
//   It often compared to wrapping - one object wraps another to extend its behaviour.


//Component
interface Coffee{
    String getDescription();
    double getCost();
}

//concrete component
class BasicCoffee implements Coffee{

    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public double getCost() {
        return 10;
    }
}

//CoffeeDecorator
abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription();
    }
    @Override
    public double getCost(){
        return coffee.getCost();
    }
}

//Milk Decorator
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + "milk";
    }
    @Override
    public double getCost() {
        return coffee.getCost() + 21;
    }
}

class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription(){
        return coffee.getDescription() + "sugar";
    }
    @Override
    public double getCost() {
        return coffee.getCost() + 2;
    }
}
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() +" "+ coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() +" "+ coffee.getCost());
    }
}
