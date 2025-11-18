from abc import ABC, abstractmethod

#The Factory Method pattern defines an interface for creating an object, but delegates the instantiation logic to
# subclasses. It allows a superclass (Creator) to defer the choice of the concrete class
# to be created (Product) to its subclasses. This decouples the client code from the specific product class it uses.

from abc import ABC, abstractmethod

# --- Product Interface ---
class Transport(ABC):
    """
    The Product interface declares the operation that all concrete products must implement.
    """
    @abstractmethod
    def deliver(self):
        pass

# --- Concrete Products ---
class ShipTransport(Transport):
    """
    Concrete Product for shipping by sea.
    """
    def deliver(self):
        print("Delivered by Ship")

class TruckTransport(Transport):
    """
    Concrete Product for shipping by road.
    """
    def deliver(self):
        print("Delivered by Truck")

# --- Creator Abstract Class ---
class Logistics(ABC):
    """
    The Creator abstract class, which declares the factory method (create_transport)
    and implements the main business logic (plan_deliver) that relies on it.
    """
    @abstractmethod
    def create_transport(self) -> Transport:
        """
        The Factory Method, which concrete creators must implement to return a product.
        """
        pass

    def plan_deliver(self):
        """
        The Template Method (Creator's main logic) that uses the product.
        """
        # The Creator calls the factory method to get a product instance
        transport = self.create_transport()
        transport.deliver()

# --- Concrete Creators ---
class ShipLogistics(Logistics):
    """
    A Concrete Creator that overrides the factory method to return a ShipTransport object.
    """
    def create_transport(self) -> Transport:
        return ShipTransport()

class TruckLogistics(Logistics):
    """
    A Concrete Creator that overrides the factory method to return a TruckTransport object.
    """
    def create_transport(self) -> Transport:
        return TruckTransport()

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # The Client works with the abstract Logistics class, decoupling it from
    # the concrete product creation (ShipTransport or TruckTransport).

    # Use Truck Logistics
    logistics: Logistics = TruckLogistics()
    logistics.plan_deliver()

    # Change to Ship Logistics
    logistics = ShipLogistics()
    logistics.plan_deliver()