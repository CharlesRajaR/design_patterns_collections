package Behavioural_Design_Patterns.Command.Efficient;

//Notes:
//=======
//1.Command pattern decouples invoker and receiver
//2.It promotes open/closed principle

//Command Interface
interface Command{
    void execute();
}
//Receiver
class Light{
    public void turnOn(){
        System.out.println("Light is on");
    }
    public void turnOff(){
        System.out.println("Light is off");
    }
}

//Concrete Commands
class LightOnCommand implements Command{
    private Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOff();
    }
}

//Invoker
class RemoteControl{
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}

//Client
public class Main {
    public static void main(String[] args) {
        Light light = new Light();

        Command turnOn = new LightOnCommand(light);
        Command turnOff = new LightOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(turnOn);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOff);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOn);
        remoteControl.pressButton();
    }
}
