from abc import ABC, abstractmethod

# --- Target Interface (Client Expected) ---
class MediaPlayer(ABC):
    """
    The Target interface that the Client (AudioPlayer) expects to use.
    """
    @abstractmethod
    def play(self, filename: str, audio_type: str):
        pass

# --- Adaptee (Incompatible Class) ---
class AdvancedMediaPlayer:
    """
    The service class that needs to be adapted. Its methods don't match the Target interface.
    """
    def play_mp4(self, file_name: str):
        print(f"Playing Mp4 file: {file_name}")

    def play_vlc(self, file_name: str):
        print(f"Playing vlc file: {file_name}")

# --- Adapter ---
class MediaAdapter(MediaPlayer):
    """
    The Adapter class that implements the Target interface (MediaPlayer)
    and holds an instance of the Adaptee (AdvancedMediaPlayer).
    It converts the Target request into the Adaptee's specific calls.
    """
    def __init__(self):
        self._advanced_media_player = AdvancedMediaPlayer()

    def play(self, filename: str, audio_type: str):
        if audio_type.lower() == "mp4":
            self._advanced_media_player.play_mp4(filename)
        elif audio_type.lower() == "vlc":
            self._advanced_media_player.play_vlc(filename)
        else:
            # This case is handled higher up in the Client, but kept here for completeness
            print("Audio type cannot be played by the adapter.")

# --- Client ---
class AudioPlayer(MediaPlayer):
    """
    The Client class that uses the Target interface (MediaPlayer).
    It handles simple cases (like mp3) directly and delegates complex cases to the Adapter.
    """
    def __init__(self):
        self._media_adapter = None

    def play(self, filename: str, audio_type: str):
        audio_type_lower = audio_type.lower()

        if audio_type_lower == "mp3":
            # Handles mp3 directly (standard behavior)
            print(f"Playing mp3 audio: {filename}")

        elif audio_type_lower in ("mp4", "vlc"):
            # Delegates incompatible types to the Adapter
            if self._media_adapter is None:
                self._media_adapter = MediaAdapter()

            self._media_adapter.play(filename, audio_type_lower)

        else:
            print("Invalid Media type")

# --- Test the Client (Main Execution) ---
if __name__ == "__main__":
    audio_player = AudioPlayer()

    audio_player.play("file1.mp3", "mp3")
    audio_player.play("file2.mp4", "mp4")
    audio_player.play("file3.vlc", "vlc")
    audio_player.play("file4.wav", "wav")