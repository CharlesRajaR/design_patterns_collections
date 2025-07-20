package Behavioural_Design_Patterns.Observer.Traditional;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        YoutubeChannel channel1 = new YoutubeChannel();
        Subscriber s1 = new Subscriber("s1");
        Subscriber s2 = new Subscriber("s2");
        Subscriber s3 = new Subscriber("s3");

        s1.subscribe(channel1);
        s2.subscribe(channel1);
        s3.subscribe(channel1);

        channel1.uploadVideo("new video song");
        channel1.uploadVideo("anjann movie song");
    }
}

class Subscriber{
    private String name;
    Subscriber(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void subscribe(YoutubeChannel channel){
        channel.addSubscriber(this);
    }
}

class YoutubeChannel{
    List<Subscriber> subscribers = new ArrayList<>();
    String recentVideo = "";
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void uploadVideo(String video){
        recentVideo = video;
        sendNotification(recentVideo);
    }

    public void sendNotification(String video){
        for(Subscriber subscriber: subscribers){
            System.out.println(video +" is uploaded! notification sent to subscriber"+subscriber.getName());
        }
    }
}

