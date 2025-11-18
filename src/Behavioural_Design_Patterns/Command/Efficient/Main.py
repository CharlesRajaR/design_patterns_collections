from abc import ABC, abstractmethod

# Command Interface (Abstract Base Class)
class Command(ABC):
    """
    The Command interface declares a method for executing a command.
    """
    @abstractmethod
    def execute(self):
        pass

# --- Receiver ---
class Light:
    """
    The Receiver knows how to perform the operations associated with the request.
    """
    def turn_on(self):
        print("Light is on")

    def turn_off(self):
        print("Light is off")

# --- Concrete Commands ---
class LightOnCommand(Command):
    """
    A Concrete Command that wraps the request to turn the light on.
    """
    def __init__(self, light: Light):
        self._light = light

    def execute(self):
        self._light.turn_on()

class LightOffCommand(Command):
    """
    A Concrete Command that wraps the request to turn the light off.
    """
    def __init__(self, light: Light):
        self._light = light

    def execute(self):
        self._light.turn_off()

# --- Invoker ---
class RemoteControl:
    """
    The Invoker holds a reference to a Command object and knows when to call its execute() method.
    """
    def __init__(self):
        self._command = None

    def set_command(self, command: Command):
        """
        Sets the command to be executed.
        """
        self._command = command

    def press_button(self):
        """
        Triggers the command execution.
        """
        if self._command:
            self._command.execute()
        else:
            print("No command set.")

# --- Client ---
if __name__ == "__main__":
    # 1. Create the Receiver object (Light)
    light = Light()

    # 2. Create the Concrete Command objects, passing the Receiver
    turn_on = LightOnCommand(light)
    turn_off = LightOffCommand(light)

    # 3. Create the Invoker object (RemoteControl)
    remote_control = RemoteControl()

    print("--- Testing Remote Control ---")

    # Set command to turn ON and press button
    remote_control.set_command(turn_on)
    remote_control.press_button()

    # Set command to turn OFF and press button
    remote_control.set_command(turn_off)
    remote_control.press_button()

    # Set command to turn ON again and press button
    remote_control.set_command(turn_on)
    remote_control.press_button()

    print("--- Test Complete ---")