# The Decorator pattern attaches additional responsibilities to an object dynamically.
# It provides a flexible alternative to subclassing for extending functionality
# by wrapping the original object with decorator objects.

from abc import ABC, abstractmethod

# --- Component Interface ---
class Coffee(ABC):
    """
    The Component interface defines the methods that both concrete components
    and decorators must implement.
    """
    @abstractmethod
    def get_description(self) -> str:
        pass

    @abstractmethod
    def get_cost(self) -> float:
        pass

# --- Concrete Component ---
class BasicCoffee(Coffee):
    """
    The base component to which responsibilities can be added.
    """
    def get_description(self) -> str:
        return "Simple coffee"

    def get_cost(self) -> float:
        return 10.0

# --- Base Decorator ---
class CoffeeDecorator(Coffee):
    """
    The abstract decorator maintains a reference to a Component object
    and forwards requests to it.
    """
    def __init__(self, coffee: Coffee):
        self._coffee = coffee

    def get_description(self) -> str:
        # Delegates to the wrapped component
        return self._coffee.get_description()

    def get_cost(self) -> float:
        # Delegates to the wrapped component
        return self._coffee.get_cost()

# --- Concrete Decorators ---
class MilkDecorator(CoffeeDecorator):
    """
    Adds milk, modifying both the description and cost.
    """
    def get_description(self) -> str:
        # Modifies the description by prepending the base description
        return self._coffee.get_description() + " with milk"

    def get_cost(self) -> float:
        # Adds the cost of milk
        return self._coffee.get_cost() + 21.0

class SugarDecorator(CoffeeDecorator):
    """
    Adds sugar, modifying both the description and cost.
    """
    def get_description(self) -> str:
        # Modifies the description
        return self._coffee.get_description() + " with sugar"

    def get_cost(self) -> float:
        # Adds the cost of sugar
        return self._coffee.get_cost() + 2.0

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Start with the base component
    coffee: Coffee = BasicCoffee()

    print("--- Basic Coffee ---")
    print(f"Description: {coffee.get_description()} | Cost: {coffee.get_cost():.2f}") # Output: Simple coffee | Cost: 10.00

    # 2. Wrap it in a MilkDecorator
    coffee = MilkDecorator(coffee)

    print("\n--- After Adding Milk ---")
    # The output is now the base description PLUS the milk description and cost
    print(f"Description: {coffee.get_description()} | Cost: {coffee.get_cost():.2f}") # Output: Simple coffee with milk | Cost: 31.00

    # 3. Wrap the *already decorated* coffee in a SugarDecorator
    coffee = SugarDecorator(coffee)

    print("\n--- After Adding Sugar ---")
    # The output is the base + milk + sugar
    print(f"Description: {coffee.get_description()} | Cost: {coffee.get_cost():.2f}") # Output: Simple coffee with milk with sugar | Cost: 33.00