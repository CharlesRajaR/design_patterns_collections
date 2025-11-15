package Structural_Design_Patterns.AdapterDesignPattern;

//ADAPTER DESIGN PATTERN:
//=======================
//1.Adapter design pattern allow interface to work together.
//2.Adapter convert the interface of one class into another interface the client expects
//3.We use this pattern if we don't want to change the legacy code or using third party code.

//Client Expected(Target interface)
interface MediaPlayer{
    void play(String filename, String audioType);
}

//Incompatible class needs to be adapted
class AdvancedMediaPlayer{
    void playMp4(String fileName){
        System.out.println("Playing Mp4 file: "+fileName);
    }

    void playVlc(String fileName){
        System.out.println("Playing vlc file: "+fileName);
    }

}

//Adapter - makes incompatible class as a compatible class
class MediaAdapter implements MediaPlayer{

    private AdvancedMediaPlayer advanceMediaPlayer;
    public MediaAdapter(){
        advanceMediaPlayer = new AdvancedMediaPlayer();
    }
    @Override
    public void play(String filename, String audioType) {
        if(audioType.equalsIgnoreCase("mp4")){
            advanceMediaPlayer.playMp4(filename);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advanceMediaPlayer.playVlc(filename);
        }else{
            System.out.println("audio type cannot be played");
        }
    }
}

//Client uses the target interface
class AudioPlayer implements MediaPlayer{
    private MediaAdapter mediaAdapter;
    @Override
    public void play(String filename, String audioType) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 audio: "+ filename);
        }else if(audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")){
            mediaAdapter = new MediaAdapter();
            mediaAdapter.play(filename, audioType);
        }else{
            System.out.println("Invalid Media type");
        }
    }
}

//Test the client
public class Main1 {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("file1", "mp3");
        audioPlayer.play("file2", "mp4");
        audioPlayer.play("file3", "vlc");
    }
}
