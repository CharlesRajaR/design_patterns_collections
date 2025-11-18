from abc import ABC, abstractmethod

# The Mediator pattern reduces coupling by centralizing communication between a set of objects (colleagues),
# preventing them from referring to each other explicitly.

# --- Mediator Interface ---
class Mediator(ABC):
    """
    The Mediator interface declares a method for communicating with colleagues (Users).
    """
    @abstractmethod
    def add_user(self, user):
        pass

    @abstractmethod
    def send_message(self, sender, message: str):
        pass

# --- User Abstract Class (Colleague) ---
class User(ABC):
    """
    The Colleague (User) abstract class. Users interact via the Mediator.
    """
    def __init__(self, mediator: Mediator, name: str):
        self._mediator = mediator
        self.name = name

    @abstractmethod
    def send(self, message: str):
        pass

    @abstractmethod
    def receive(self, message: str):
        pass

# --- Concrete User (Concrete Colleague) ---
class ConcreteUser(User):
    """
    Concrete Users communicate with other colleagues through their Mediator.
    """
    def __init__(self, mediator: Mediator, name: str):
        super().__init__(mediator, name)

    def send(self, message: str):
        print(f"{self.name} sends the message: {message}")
        self._mediator.send_message(self, message)

    def receive(self, message: str):
        print(f"{self.name} receives the message: {message}")

# --- Concrete Mediator ---
class MediatorImpl(Mediator):
    """
    The Concrete Mediator coordinates communication among colleagues.
    """
    def __init__(self):
        self._users = []

    def add_user(self, user: User):
        self._users.append(user)

    def send_message(self, sender: User, message: str):
        # Iterate through all users and send the message, excluding the sender
        for user in self._users:
            if user is not sender:
                user.receive(message)

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Create the Mediator
    mediator = MediatorImpl()

    # 2. Create the Users (Colleagues)
    user1 = ConcreteUser(mediator, "user1")
    user2 = ConcreteUser(mediator, "user2")
    user3 = ConcreteUser(mediator, "user3")

    # 3. Add Users to the Mediator
    print("--- Initial Setup (user1, user3 added) ---")
    mediator.add_user(user1)
    mediator.add_user(user3)

    # user3 sends a message (user1 receives)
    user3.send("hi")

    # Add user2
    print("\n--- Adding user2 ---")
    mediator.add_user(user2)

    # user3 sends a message (user1 and user2 receive)
    user3.send("hi user2")

    # user1 sends a message (user2 and user3 receive)
    print("\n--- user1 sends a message ---")
    user1.send("hello")