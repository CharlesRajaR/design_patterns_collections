# The Facade pattern provides a simplified, unified interface to a complex subsystem.
# It hides the complexity of multiple classes (subsystems), making the system easier for the client to use.
#

[Image of Facade design pattern UML diagram]


# --- Subsystem 1: DvdPlayer ---
class DvdPlayer:
    def on(self):
        print("dvd player is on")

    def play(self, movie: str):
        print(f"{movie} movie is playing")

    def off(self):
        print("dvd player is off")

# --- Subsystem 2: Projector ---
class Projector:
    def on(self):
        print("projector is on")

    def wide_screen_mode(self):
        print("projector is in wide screen mode")

    def off(self):
        print("projector is off")

# --- Subsystem 3: SoundSystem ---
class SoundSystem:
    def on(self):
        print("sound system is on")

    def set_volume(self, level: int):
        print(f"volume set to {level}")

    def off(self):
        print("sound system is off")

# --- Facade: HomeTheatreFacade ---
class HomeTheatreFacade:
    """
    The Facade provides a simple interface to a set of objects in the subsystem.
    """
    def __init__(self, dvd_player: DvdPlayer, projector: Projector, sound_system: SoundSystem):
        self._dvd_player = dvd_player
        self._projector = projector
        self._sound_system = sound_system

    def watch_movie(self, movie_name: str):
        """
        A single, high-level operation that orchestrates the complex sequence of subsystem calls.
        """
        print("\n🎬 Get ready to watch a movie...")
        self._dvd_player.on()
        self._projector.on()
        self._projector.wide_screen_mode()
        self._sound_system.on()
        self._sound_system.set_volume(10)
        self._dvd_player.play(movie_name)

    def end_movie(self):
        """
        A single, high-level operation to shut down the subsystem.
        """
        print("\n😴 Shutting movie theatre down...")
        self._dvd_player.off()
        self._projector.off()
        self._sound_system.off()

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Instantiate the subsystem components
    dvd_player = DvdPlayer()
    sound_system = SoundSystem()
    projector = Projector()

    # 2. Instantiate the Facade, passing in the components
    home_theatre_facade = HomeTheatreFacade(dvd_player, projector, sound_system)

    # 3. Client uses the simple Facade methods, hiding all the complexity
    home_theatre_facade.watch_movie("Inception")

    home_theatre_facade.end_movie()