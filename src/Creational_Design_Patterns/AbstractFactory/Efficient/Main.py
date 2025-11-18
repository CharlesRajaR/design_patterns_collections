from abc import ABC, abstractmethod

# The Abstract Factory pattern provides an interface for creating families of related or dependent objects without
# specifying their concrete classes. It achieves this by delegating object creation to specific factory implementations.
# This ensures that the client code only interacts with abstract types, promoting loose coupling and making it easy
# to swap out entire product families.


# --- Product Interface ---
class Vehicle(ABC):
    """
    The Product interface declares the operations that all concrete products must implement.
    """
    @abstractmethod
    def start(self):
        pass

    @abstractmethod
    def stop(self):
        pass

# --- Concrete Products ---
class Honda(Vehicle):
    """
    Concrete Product implementation.
    """
    def start(self):
        print("honda is started")

    def stop(self):
        print("honda is stopped")

class BMW(Vehicle):
    """
    Concrete Product implementation.
    """
    def start(self):
        print("bmw is started")

    def stop(self):
        print("BMW is stopped")

class Toyota(Vehicle):
    """
    Concrete Product implementation.
    """
    def start(self):
        print("toyota is started")

    def stop(self):
        print("toyota is stopped")

# --- Creator Interface ---
class VehicleFactory(ABC):
    """
    The Creator interface declares the factory method, which returns an object of the Product type.
    """
    @abstractmethod
    def create_vehicle(self) -> Vehicle:
        pass

# --- Concrete Creators ---
class HondaFactory(VehicleFactory):
    """
    Concrete Creator implements the factory method to return a specific Concrete Product (Honda).
    """
    def create_vehicle(self) -> Vehicle:
        return Honda()

class BMWFactory(VehicleFactory):
    """
    Concrete Creator implements the factory method to return a specific Concrete Product (BMW).
    """
    def create_vehicle(self) -> Vehicle:
        return BMW()

class ToyotaFactory(VehicleFactory):
    """
    Concrete Creator implements the factory method to return a specific Concrete Product (Toyota).
    """
    def create_vehicle(self) -> Vehicle:
        return Toyota()

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # Client works with the Creator interface (VehicleFactory)

    print("--- Using Honda Factory ---")
    honda_factory = HondaFactory()
    vehicle_honda = honda_factory.create_vehicle()
    vehicle_honda.start()
    vehicle_honda.stop()

    print("\n--- Using BMW Factory ---")
    bmw_factory = BMWFactory()
    vehicle_bmw = bmw_factory.create_vehicle()
    vehicle_bmw.start()
    vehicle_bmw.stop()

    print("\n--- Using Toyota Factory ---")
    toyota_factory = ToyotaFactory()
    vehicle_toyota = toyota_factory.create_vehicle()
    vehicle_toyota.start()
    vehicle_toyota.stop()