package Creational_Design_Patterns.Builder.Efficient;

class Pizza{
    private String base;
    private String cheese;
    private String toppings;

    private Pizza(Builder builder){
        this.base = builder.base;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
    }

    public void viewIngredients(){
        System.out.println(String.format("Base: %s\nCheese: %s\nToppings: %s", base, cheese, toppings));
    }

    public static class Builder{
        private String base = "default base";
        private String cheese = "default cheese";
        private String toppings = "default toppings";

        public Builder setBase(String base){
            this.base = base;
            return this;
        }

        public Builder setCheese(String cheese){
            this.cheese = cheese;
            return this;
        }

        public Builder setToppings(String toppings){
            this.toppings = toppings;
            return this;
        }

        public Pizza build(){
            Pizza pizza = new Pizza(this);
            return pizza;
        }

    }
}
public class Main2 {

    public static void main(String[] args) {
        Pizza.Builder pizzaBuilder = new Pizza.Builder();


        Pizza pizza = pizzaBuilder.setBase("bread").setToppings("tomato").build();

        pizza.viewIngredients();
    }
}
