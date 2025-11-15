package Behavioural_Design_Patterns.StateDesignPattern;

//                ==================================
//                |     STATE DESIGN PATTERN       |
//                ==================================
//   It allows an object to change its behaviour with respect to the internal state changes.
//   In short instead of using multiple if else checking to check the state. the object delegates its behaviour to different state classes.


//State interface
interface State{
    //context is the behaviour
    void handle(Context context);
}

//This is the class whose behaviour depends on state
//
class Context{
    private State state;

    public Context(){
        this.state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}

//Concrete state class
class StartState implements State {

    @Override
    public void handle(Context context) {
        System.out.println("start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}

class StopState implements State {

    @Override
    public void handle(Context context) {
        System.out.println("stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.handle(context);

        System.out.println("current state "+context.getState().toString());

        StopState stopState = new StopState();
        stopState.handle(context);

        System.out.println("current state "+context.getState().toString());
    }
}
