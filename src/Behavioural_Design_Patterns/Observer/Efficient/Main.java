package Behavioural_Design_Patterns.Observer.Efficient;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       YoutubeChannel channel = new YoutubeChannelImpl();
       Subscriber s1 = new YoutubeSubscriber();
       Subscriber s2 = new EmailSubscriber();
       s1.subscribe(channel);
       s2.subscribe(channel);
       channel.uploadVideo("v1");
       channel.uploadVideo("v2");
       s2.unSubscribe(channel);
       channel.uploadVideo("v3");

    }
}

interface Subscriber{
    void subscribe(YoutubeChannel channel);
    void unSubscribe(YoutubeChannel channel);
    void update(String video);
}

class YoutubeSubscriber implements Subscriber{
    @Override
    public void subscribe(YoutubeChannel channel) {
      channel.addSubscriber(this);
    }

    @Override
    public void unSubscribe(YoutubeChannel channel) {
        channel.removeSubscriber(this);
    }

    @Override
    public void update(String video) {
        System.out.println("youtube notification sent for video : "+video);
    }
}

class EmailSubscriber implements Subscriber{

    @Override
    public void subscribe(YoutubeChannel channel) {
        channel.addSubscriber(this);
    }

    @Override
    public void unSubscribe(YoutubeChannel channel) {
        channel.removeSubscriber(this);
    }

    @Override
    public void update(String video) {
        System.out.println("email notification sent successfully for video : "+video);
    }
}

interface YoutubeChannel{
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void uploadVideo(String video);
    void notify(String video);
}
class YoutubeChannelImpl implements YoutubeChannel{
    private String video;
    List<Subscriber> subscriberList = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber){
        subscriberList.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }
    @Override
    public void uploadVideo(String video) {
         this.video = video;
         notify(video);
    }
    @Override
    public void notify(String video) {
      for(Subscriber s: subscriberList){
          s.update(video);
      }
    }
}