package Behavioural_Design_Patterns.Iterator.Efficient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
       Playlist pl = new Playlist();
       for(int i = 0; i < 100; i++){
           pl.addSong("song-"+(i+1));
       }

       Iterator simple = pl.iterator("simple");
       System.out.println("song playing one by one");
       while (simple.hasNext()){
           System.out.println(simple.next());
       }
        System.out.println(simple.next());

       Iterator shuffle = pl.iterator("shuffle");
        System.out.println("song playing in shuffle mode");
       while (shuffle.hasNext()){
           System.out.println(shuffle.next());
       }
        System.out.println(shuffle.next());
    }
}

interface Iterator{
    boolean hasNext();
    String next();
}
class Playlist{
    List<String> songs;
    Playlist(){
        songs = new ArrayList<>();
    }

    public void addSong(String song){
        songs.add(song);
    }

    public void removeSong(String song){
        songs.remove(song);
    }

    public List<String> getSongs(){
        return songs;
    }

    public Iterator iterator(String name) throws Exception {
        switch (name){
            case "simple":
                return new PlaylistIterator(this);

            case "shuffle":
                return new ShuffleIterator(this);

            default:
                break;
        }
        throw new Exception("iterator not found");
    }
}
class PlaylistIterator implements Iterator{
    private List<String> songs;

    int index;
    PlaylistIterator(Playlist playlist){
        this.songs = playlist.getSongs();
        index = 0;
    }
    @Override
    public boolean hasNext() {
        if(index < songs.size()-1){
            return true;
        }
        return false;
    }

    @Override
    public String next() {
        return songs.get(index++);
    }
}


class ShuffleIterator implements Iterator{
    private List<String> songs;
    int index;
    ShuffleIterator(Playlist playlist){
        List<String> shuffled = new ArrayList<>(playlist.getSongs());
        Collections.shuffle(shuffled);
        this.songs = shuffled;
        index = 0;
    }
    @Override
    public boolean hasNext() {
        if(index < songs.size()-1){
            return true;
        }
        return false;
    }

    @Override
    public String next() {
        return songs.get(index++);
    }
}
