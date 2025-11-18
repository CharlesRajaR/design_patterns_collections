# The Prototype pattern is a creational pattern used to create new objects
# by copying an existing object (the prototype) instead of using the 'new' keyword.
# It uses cloning to avoid the overhead of repeated object initialization.


# import copy     _______________________________
# =============== |PYTHON DEEP COPY USAGE EXAMPLE| ===================================
#                 -------------------------------                                   ||
# # 1. Define a complex object (a list containing a list and a dictionary)          ||
# original_list = [1, [2, 3], {"a": 4, "b": 5}]                                     ||
#                                                                                   ||
# # 2. Perform a DEEP COPY                                                          ||
# # The deep copy recursively copies everything, creating new independent objects   ||
# # for the nested elements (the inner list and the dictionary).                    ||
# deep_copied_list = copy.deepcopy(original_list)                                   ||
#                                                                                   ||
# print("--- Initial State ---")                                                    ||
# print(f"Original: {original_list}")                                               ||
# print(f"Deep Copy: {deep_copied_list}")                                           ||
# print("-" * 25)                                                                   ||
#                                                                                   ||
# # 3. Modify a nested element in the DEEP COPY                                     ||
# deep_copied_list[1][0] = 99  # Modifying the nested list                          ||
# deep_copied_list[2]["a"] = 88  # Modifying the nested dictionary                  ||
#                                                                                   ||
# print("--- After Modification on Deep Copy ---")                                  ||
# print(f"Modified Deep Copy: {deep_copied_list}")                                  ||
# print(f"Original List: {original_list}") # The original remains unchanged         ||
#                                                                                   ||
# # Verify that the nested objects are different objects in memory                  ||
# print("\n--- Memory Check ---")                                                   ||
# print(f"Original nested list ID: {id(original_list[1])}")                         ||
# print(f"Deep Copy nested list ID: {id(deep_copied_list[1])}")                     ||
#                                                                                   ||
#====================================================================================

from abc import ABC, abstractmethod

# --- Prototype Interface ---
class Prototype(ABC):
    """
    Declares an interface for cloning itself.
    Python's standard library provides 'copy.copy' and 'copy.deepcopy'.
    """
    @abstractmethod
    def clone(self) -> 'Prototype':
        """Abstract method to return a copy of the object."""
        pass

# --- Concrete Prototype ---
class Circle(Prototype):
    """
    The object that provides the clone functionality.
    """
    def __init__(self, radius: int, color: str = None):
        self._radius = radius
        self._color = color
        if color is None:
            print(f"Circle created with radius {radius} (no color)")
        else:
            print(f"Circle created with radius {radius} and color {color}")

    def set_color(self, color: str):
        self._color = color

    # Implementing deep clone functionality
    def clone(self) -> 'Circle':

        # We explicitly call the constructor to achieve a clean deep copy behavior
        return Circle(self._radius, self._color)

    def show(self):
        print(f"Radius of the circle = {self._radius}")
        print(f"Color of the circle = {self._color}")

# --- Registry (CareTaker) ---
class Registry:
    """
    Manages and stores the prototype instances.
    It returns a clone of the stored prototype upon request.
    """
    def __init__(self):
        # Dictionary (equivalent to HashMap) to store prototypes
        self._registry = {}

    def add_registry(self, prototype: Prototype, name: str):
        print(f"Added {name}")
        self._registry[name] = prototype

    def get_clone(self, name: str) -> Prototype:
        """
        Retrieves the prototype by name and returns a clone of it.
        """
        prototype = self._registry.get(name)

        if prototype is None:
            print("No prototype is found, so instead of cloning do create new one")
            return None

        # Returns a clone using the prototype's defined clone method
        return prototype.clone()

# --- Client (Main Execution) ---
if __name__ == "__main__":
    registry = Registry()

    # 1. Create and register the prototype instances (these are the initial models)
    circle_red = Circle(10, "red")
    registry.add_registry(circle_red, "red_circle")

    circle_green = Circle(20, "green")
    registry.add_registry(circle_green, "green_circle")

    circle_blue = Circle(30, "blue")
    registry.add_registry(circle_blue, "blue_circle")

    print("\n--- Cloning Process ---")

    # 2. Get a clone of the "green_circle" prototype from the registry
    # This avoids initializing a new Circle from scratch.
    cloned_circle: Circle = registry.get_clone("green_circle")

    if cloned_circle is not None:
        cloned_circle.show()

    # Test cloning a non-existent prototype
    registry.get_clone("yellow_circle")