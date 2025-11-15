package Creational_Design_Patterns.Prototype;

import java.util.HashMap;

interface Prototype extends Cloneable{
    Prototype clone();
}

class Circle implements Prototype{
    int radius;
    String color;

    Circle(int radius){
        this.radius = radius;
    }

    public Circle(int radius, String color){
        this.radius = radius;
        this.color = color;
    }

    public void setColor(String color){
        this.color = color;
    }
    //deep clone
    @Override
    public Prototype clone() {
        return new Circle(radius, color);
    }

    public void show(){
        System.out.println(String.format("Radius of the circle = %d\nColor of the circle = %s", radius, color));
    }
}

class Registry{
    private HashMap<String, Prototype> registry = new HashMap<>();

    public void addRegistry(Prototype prototype, String name){
        System.out.println("Added "+name);
        registry.put(name, prototype);
    }

    public Prototype getClone(String name){
//        if(!registry.containsKey("name")){
//            System.out.println("no prototype is found, so instead of cloning do create new one");
//            return null;
//        }

        if(registry.getOrDefault(name, null) == null){
            System.out.println("no prototype is found, so instead of cloning do create new one");
            return null;
        }
        return registry.get(name).clone();
    }
}
public class Main1 {
    public static void main(String[] args) {
        Registry registry = new Registry();

        Circle circle = new Circle(10, "red");
        registry.addRegistry(circle, "red_circle");
        circle = new Circle(10, "green");
        registry.addRegistry(circle, "green_circle");
        circle = new Circle(10, "blue");
        registry.addRegistry(circle, "blue_circle");

//        HashMap<String, Prototype> reg = new HashMap<>();
//        reg.put("eg", circle);
////        Circle cloned = (Circle) circle.clone();
//        Circle cloned = (Circle) reg.get("eg").clone();

        Circle cloned = (Circle) registry.getClone("green_circle");
        if(cloned != null) cloned.show();
    }
}
