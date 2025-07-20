package Behavioural_Design_Patterns.Command.Traditional;

public class Main {
    public static void main(String[] args) {
       Tv tv1 = new Tv("samsung");
       Remote remote = new Remote(tv1);
       remote.on();
       remote.off();
       Tv tv2 = new Tv("lg");
       remote.setTv(tv2);
       remote.on();
    }
}

class Remote{
    Tv tv;
    Remote(Tv tv){
        this.tv = tv;
    }

    public void setTv(Tv tv){
        this.tv = tv;
    }

    public void on(){
        tv.turnOn();
    }
    public void off(){
        tv.turnOff();
    }

    public void volumeUp(){
        tv.volumeUp();
    }

    public void volumeDown(){
        tv.volumeDown();
    }
}
class Tv{
    private String name;
    Tv(String name){
        this.name = name;
    }
    public void turnOn(){
        System.out.println("tv: "+name+" is on");
    }

    public void turnOff(){
        System.out.println("tv is off");
    }

    public void volumeUp(){
        System.out.println("volume is increased");
    }

    public void volumeDown(){
        System.out.println("volume is decreased");
    }
}
