from abc import ABC, abstractmethod

# --- Observer Interface (Subscriber) ---
class Subscriber(ABC):
    """
    The Observer pattern defines a one-to-many dependency between objects.
    When one object (the Subject) changes state, all its dependents (Observers) are notified
    and updated automatically, promoting loose coupling.
    """
    @abstractmethod
    def subscribe(self, channel):
        pass

    @abstractmethod
    def un_subscribe(self, channel):
        pass

    @abstractmethod
    def update(self, video: str):
        pass

# --- Subject Interface (YoutubeChannel) ---
class YoutubeChannel(ABC):
    """
    The Subject interface declares management methods for attaching and detaching Observers.
    """
    @abstractmethod
    def add_subscriber(self, subscriber: Subscriber):
        pass

    @abstractmethod
    def remove_subscriber(self, subscriber: Subscriber):
        pass

    @abstractmethod
    def upload_video(self, video: str):
        pass

    @abstractmethod
    def notify(self, video: str):
        pass

# --- Concrete Subject ---
class YoutubeChannelImpl(YoutubeChannel):
    """
    The Concrete Subject maintains the state and notifies observers when the state changes.
    """
    def __init__(self):
        self._video = None
        self._subscriber_list = []

    def add_subscriber(self, subscriber: Subscriber):
        print(f"Subscriber {type(subscriber).__name__} added.")
        self._subscriber_list.append(subscriber)

    def remove_subscriber(self, subscriber: Subscriber):
        print(f"Subscriber {type(subscriber).__name__} removed.")
        self._subscriber_list.remove(subscriber)

    def upload_video(self, video: str):
        self._video = video
        print(f"\n[Channel] Video '{video}' uploaded.")
        self.notify(video)

    def notify(self, video: str):
        print(f"[Channel] Notifying {len(self._subscriber_list)} subscribers...")
        for s in self._subscriber_list:
            s.update(video)

# --- Concrete Observers ---
class YoutubeSubscriber(Subscriber):
    """
    A Concrete Observer that receives notifications via a YouTube notification.
    """
    def subscribe(self, channel: YoutubeChannel):
        channel.add_subscriber(self)

    def un_subscribe(self, channel: YoutubeChannel):
        channel.remove_subscriber(self)

    def update(self, video: str):
        print(f"    -> YouTube notification sent for video : {video}")

class EmailSubscriber(Subscriber):
    """
    A Concrete Observer that receives notifications via email.
    """
    def subscribe(self, channel: YoutubeChannel):
        channel.add_subscriber(self)

    def un_subscribe(self, channel: YoutubeChannel):
        channel.remove_subscriber(self)

    def update(self, video: str):
        print(f"    -> Email notification sent successfully for video : {video}")

# --- Client (Main Execution) ---
if __name__ == "__main__":
    channel = YoutubeChannelImpl()
    s1 = YoutubeSubscriber()
    s2 = EmailSubscriber()

    # s1 and s2 subscribe to the channel
    s1.subscribe(channel)
    s2.subscribe(channel)

    # First upload: Both s1 and s2 are notified
    channel.upload_video("v1")

    # Second upload: Both s1 and s2 are notified
    channel.upload_video("v2")

    # s2 unsubscribes
    s2.un_subscribe(channel)

    # Third upload: Only s1 is notified
    channel.upload_video("v3")