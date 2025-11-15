package Structural_Design_Patterns.FacadeDesignPattern;

//                        ====================================
//                        |      FACADE DESIGN PATTERN       |
//                        ====================================
//   It provides a simplified unified interface to a complex subsystem. It hides the complexity of multiple classes making the subsystem easier to use


//Subsystem 1
class DvdPlayer{
    public void on(){
        System.out.println("dvd player is on");
    }

    public void play(String movie){
        System.out.println(movie+" movie is playing");
    }

    public void off(){
        System.out.println("dvd player is off");
    }
}

//Subsystem 2
class Projector{
    public void on(){
        System.out.println("projector is on");
    }

    public void wideScreenMode(){
        System.out.println("projector is in wide screen mode");
    }

    public void off(){
        System.out.println("projector is off");
    }
}

//Subsystem 3
class SoundSystem{
    public void on(){
        System.out.println("sound system is on");
    }

    public void setVolume(int level){
        System.out.println("volume set to "+level);
    }

    public void off(){
        System.out.println("sound system is off");
    }
}

//Home Theatre Facade
class HomeTheatreFacade{
    private DvdPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;

    public HomeTheatreFacade(DvdPlayer dvdPlayer, Projector projector, SoundSystem soundSystem){
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
    }

    public void watchMovie(String movieName){
        System.out.println("Get ready to watch a movie...");
        dvdPlayer.on();
        projector.on();
        projector.wideScreenMode();
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.play(movieName);
    }

    public void endMovie(){
        System.out.println("shutting movie theatre down...");
        dvdPlayer.off();
        projector.off();
        soundSystem.off();
    }
}

//Client
public class Main {
    public static void main(String[] args) {
        DvdPlayer dvdPlayer = new DvdPlayer();
        SoundSystem soundSystem = new SoundSystem();
        Projector projector = new Projector();

        HomeTheatreFacade homeTheatreFacade = new HomeTheatreFacade(dvdPlayer, projector, soundSystem);
        homeTheatreFacade.watchMovie("Inception");
        System.out.println();
        homeTheatreFacade.endMovie();
    }
}
