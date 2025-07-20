package Behavioural_Design_Patterns.Iterator.Traditional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("ek tho then");
        playlist.addSong("jigiru jigiru jigiru");
        playlist.addSong("ennaa sonaa");

        playlist.play();
        System.out.println();
        playlist.PlayShuffle();

    }
}

class Playlist{
    List<String> songs = new ArrayList<>();

    public void addSong(String song){
        songs.add(song);
    }

    public void play(){
        for(int i = 0; i < songs.size(); i++){
            System.out.println("the song "+songs.get(i)+" is playing now");
        }
    }

    public void PlayShuffle(){
        List<String> shuffle = new ArrayList<>(songs);
        Collections.shuffle(shuffle);
        for(int i = 0; i < shuffle.size(); i++){
            System.out.println("the song "+shuffle.get(i)+" is playing now");
        }
    }
}
