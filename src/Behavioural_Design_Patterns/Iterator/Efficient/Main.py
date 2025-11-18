import random
from abc import ABC, abstractmethod

# Iterator Interface (Abstract Base Class)
class Iterator(ABC):
    """
    The Iterator interface declares the methods required for traversing a collection.
    """
    @abstractmethod
    def has_next(self) -> bool:
        pass

    @abstractmethod
    def next(self) -> str:
        pass

# --- Aggregate (Collection) ---
class Playlist:
    """
    The Aggregate class that creates the appropriate Iterator object.
    """
    def __init__(self):
        self.songs = []

    def add_song(self, song: str):
        self.songs.append(song)

    def remove_song(self, song: str):
        self.songs.remove(song)

    def get_songs(self) -> list[str]:
        # Return a copy to prevent external modification of the internal list
        return list(self.songs)

    def iterator(self, name: str) -> Iterator:
        """
        Factory method to create different types of iterators.
        """
        if name == "simple":
            return PlaylistIterator(self)
        elif name == "shuffle":
            return ShuffleIterator(self)
        else:
            raise ValueError("Iterator not found: " + name)

# --- Concrete Iterators ---
class PlaylistIterator(Iterator):
    """
    Concrete Iterator for sequential (simple) traversal.
    """
    def __init__(self, playlist: Playlist):
        self._songs = playlist.get_songs()
        self._index = 0

    def has_next(self) -> bool:
        # Check if the next index is within the bounds of the list
        return self._index < len(self._songs)

    def next(self) -> str:
        if not self.has_next():
            # In Python, we typically raise StopIteration.
            # we'll return a message after the list is exhausted.
            return "End of playlist reached."

        song = self._songs[self._index]
        self._index += 1
        return song


class ShuffleIterator(Iterator):
    """
    Concrete Iterator for shuffled traversal.
    """
    def __init__(self, playlist: Playlist):
        # Create a shuffled copy of the songs
        shuffled_songs = playlist.get_songs()
        random.shuffle(shuffled_songs)
        self._songs = shuffled_songs
        self._index = 0

    def has_next(self) -> bool:
        # Check if the next index is within the bounds of the list
        return self._index < len(self._songs)

    def next(self) -> str:
        if not self.has_next():
            return "End of playlist reached."

        song = self._songs[self._index]
        self._index += 1
        return song


# --- Client (Main Execution) ---
if __name__ == "__main__":
    pl = Playlist()
    for i in range(10): # Using 10 songs for easier verification of shuffle
        pl.add_song(f"song-{i+1}")

    print("--- Simple Iterator Test ---")
    simple = pl.iterator("simple")
    print("Songs playing one by one (Simple mode):")
    while simple.has_next():
        print(simple.next())
    # Calling next() after has_next() is false
    print(f"Final call: {simple.next()}")

    print("\n--- Shuffle Iterator Test ---")
    shuffle = pl.iterator("shuffle")
    print("Songs playing in shuffle mode:")
    while shuffle.has_next():
        print(shuffle.next())
    # Calling next() after has_next() is false
    print(f"Final call: {shuffle.next()}")