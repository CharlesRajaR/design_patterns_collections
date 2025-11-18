# --- Memento Class ---
class Memento:
    """
    The Memento class stores the internal state of the Originator object.
    It should be immutable and typically only expose getters.
    """
    def __init__(self, state: str, name: str):
        self._state = state
        self._name = name

    def get_state(self) -> str:
        return self._state

    def get_name(self) -> str:
        return self._name

# --- Originator Class ---
class Originator:
    """
    The Originator is the object whose state can be saved and restored.
    It creates a Memento and uses a Memento to restore its previous state.
    """
    def __init__(self, state: str):
        self._state = state
        print(f"Originator initialized. Current state is {self._state}")

    def set_state(self, state: str):
        self._state = state
        print(f"State changed. Current state is {self._state}")

    def get_state(self) -> str:
        return self._state

    def save_state_to_memento(self, name: str) -> Memento:
        """
        Creates a Memento containing the current internal state.
        """
        return Memento(self._state, name)

    def restore_state_from_memento(self, memento: Memento):
        """
        Restores the Originator's state from a Memento object.
        """
        self._state = memento.get_state()
        print(f"State '{memento.get_name()}' restored. Current state is {self._state}")

# --- CareTaker Class ---
class CareTaker:
    """
    The CareTaker manages the collection of Mementos.
    It never operates on or examines the contents of a Memento.
    """
    def __init__(self):
        # Using a dictionary (HashMap equivalent) to store Mementos by name
        self._memento_list = {}

    def add_memento(self, memento: Memento):
        self._memento_list[memento.get_name()] = memento

    def get_memento(self, name: str) -> Memento:
        return self._memento_list.get(name)

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # Create the CareTaker and Originator
    care_taker = CareTaker()
    originator = Originator("state1") # Initial state

    # Save state1 to a Memento and store it in the CareTaker
    memento_state1 = originator.save_state_to_memento("state1_checkpoint")
    care_taker.add_memento(memento_state1)

    print("-" * 20)

    # Change state twice
    originator.set_state("state2")
    originator.set_state("state3")

    print("-" * 20)

    # Restore the state from the saved Memento
    # The CareTaker retrieves the Memento
    memento_to_restore = care_taker.get_memento("state1_checkpoint")

    # The Originator restores its state from the Memento
    if memento_to_restore:
        originator.restore_state_from_memento(memento_to_restore)
    else:
        print("Memento not found.")