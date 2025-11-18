from abc import ABC, abstractmethod

# The State pattern allows an object (the Context)
# to alter its behavior when its internal state changes. The object appears to change its class.

# --- Context Class ---
class Context:
    """
    The Context maintains an instance of a ConcreteState subclass that defines
    the current behavior of the Context.
    """
    def __init__(self):
        # Initial state is null
        self._state = None

    def set_state(self, state):
        """
        Allows external parties (or State objects) to change the state.
        """
        self._state = state

    def get_state(self):
        return self._state

# --- State Interface ---
class State(ABC):
    """
    The State interface declares a method for the Context to execute.
    """
    @abstractmethod
    def handle(self, context: Context):
        pass

# --- Concrete State Classes ---
class StartState(State):
    """
    Implements the behavior associated with a state of the Context.
    """
    def handle(self, context: Context):
        print("start state")
        # Change the state of the Context to the current state object
        context.set_state(self)

    def __str__(self):
        return "Start State"

class StopState(State):
    """
    Implements the behavior associated with another state of the Context.
    """
    def handle(self, context: Context):
        print("stop state")
        # Change the state of the Context to the current state object
        context.set_state(self)

    def __str__(self):
        return "Stop State"

# --- Client (Main Execution) ---
if __name__ == "__main__":
    context = Context()

    # Transition to StartState
    start_state = StartState()
    start_state.handle(context)

    # Check the current state
    print(f"current state {context.get_state()}")

    # Transition to StopState
    stop_state = StopState()
    stop_state.handle(context)

    # Check the current state
    print(f"current state {context.get_state()}")