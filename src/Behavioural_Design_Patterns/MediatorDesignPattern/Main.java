package Behavioural_Design_Patterns.MediatorDesignPattern;

//               ===========================
//               | MEDIATOR DESIGN PATTERN |
//               ===========================
// The mediator encapsulates how a set of objects interact.
// It promotes loose coupling by preventing direct communication between objects instead communication happens via the mediator.


import java.util.ArrayList;
import java.util.List;

abstract class User{
    protected Mediator mediator;
    protected String name;

    public User(Mediator mediator, String name){
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class ConcreteUser extends User{
    public ConcreteUser(Mediator mediator, String name){
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name+ " sends the message: "+message);
        mediator.sendMessage(this, message);
    }

    @Override
    public void receive(String message) {
        System.out.println(name+" receives the message: "+message);
    }
}



interface Mediator{
    public void adduser(User user);
    public void sendMessage(User sender, String message);
}

class MediatorImpl implements Mediator{
    List<User> users = new ArrayList<>();
    @Override
    public void adduser(User user){
        users.add(user);
    }

    public void sendMessage(User sender, String message) {
         for(User user: users){
             if(user != sender){
                  user.receive(message);
             }
         }
    }
}
public class Main {
    public static void main(String[] args) {
        Mediator mediator = new MediatorImpl();

        User user1 = new ConcreteUser(mediator, "user1");
        User user2 = new ConcreteUser(mediator, "user2");
        User user3 = new ConcreteUser(mediator, "user3");

        mediator.adduser(user1);
        mediator.adduser(user3);

        user3.send("hi");

        mediator.adduser(user2);
        user3.send("hi user2");

        user1.send("hello");
    }
}
