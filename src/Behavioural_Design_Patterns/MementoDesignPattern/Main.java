package Behavioural_Design_Patterns.MementoDesignPattern;


//                       =============================
//                       |   MEMENTO DESIGN PATTERN  |
//                       =============================
//       It allows you to save and restore object internal state without violating encapsulation


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Memento class
class Memento{
    private String state;
    private String name;

    public Memento(String state, String name){
        this.state = state;
        this.name = name;
    }

    public String getState(){
        return state;
    }

    public String getName(){
        return name;
    }
}

//The object whose state will be saved and restored - Originator

class Originator{
    private String state;

    public Originator(String state){
        System.out.println("Current state is "+state);
        this.state = state;
    }

    public void setState(String state){
        System.out.println("Current state is "+state);
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(String name){
        return new Memento(state, name);
    }

    public void restoreStateFromMemento(Memento memento){
        state = memento.getState();
        System.out.println("state  "+state+" is restored");
    }
}

//CareTaker - manages multiple mementos
class CareTaker{
    private HashMap<String, Memento> mementoList = new HashMap<>();

    public void addMemento(Memento memento){
        mementoList.put(memento.getName(), memento);
    }

    public Memento getMemento(String name){
        return mementoList.get(name);
    }

}
public class Main {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator("state1");
        careTaker.addMemento(originator.saveStateToMemento("state1"));
        originator.setState("state2");
        originator.setState("state3");

        originator.restoreStateFromMemento(careTaker.getMemento("state1"));
    }
}
