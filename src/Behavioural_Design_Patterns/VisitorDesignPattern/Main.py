from abc import ABC, abstractmethod

# --- Visitor Interface ---
class Visitor(ABC):
    """
    The Visitor interface declares a set of visiting methods for all concrete elements.
    It separates the algorithm (operation) from the object structure.
    """
    @abstractmethod
    def visit_book(self, book):
        pass

    @abstractmethod
    def visit_fruit(self, fruit):
        pass

# --- Element Interface ---
class ItemElement(ABC):
    """
    The Element interface declares an 'accept' method that takes the Visitor as an argument.
    """
    @abstractmethod
    def accept(self, visitor: Visitor) -> int:
        pass

# --- Concrete Elements ---
class Book(ItemElement):
    """
    A concrete element that implements the accept method.
    """
    def __init__(self, price: int, isbn_number: str):
        self._price = price
        self._isbn_number = isbn_number

    def get_price(self) -> int:
        return self._price

    def get_isbn_number(self) -> str:
        return self._isbn_number

    def accept(self, visitor: Visitor) -> int:
        # Calls the appropriate visit method on the visitor
        return visitor.visit_book(self)

class Fruit(ItemElement):
    """
    Another concrete element.
    """
    def __init__(self, price_per_kg: int, weight: int, name: str):
        self._price_per_kg = price_per_kg
        self._weight = weight
        self._name = name

    def get_weight(self) -> int:
        return self._weight

    def get_price_per_kg(self) -> int:
        return self._price_per_kg

    def get_name(self) -> str:
        return self._name

    def accept(self, visitor: Visitor) -> int:
        # Calls the appropriate visit method on the visitor
        return visitor.visit_fruit(self)

# --- Concrete Visitor ---
class VisitorImpl(Visitor):
    """
    A concrete visitor that implements the specific algorithm for each element type.
    """
    def visit_book(self, book: Book) -> int:
        # Algorithm logic for books (e.g., whether a discount applies)
        return book.get_price()

    def visit_fruit(self, fruit: Fruit) -> int:
        # Algorithm logic for fruits
        return fruit.get_weight() * fruit.get_price_per_kg()

# --- Client (Main Execution) ---
def calculate_total(elements: list[ItemElement]) -> int:
    """
    The client logic that iterates over elements and triggers the visit process.
    """
    visitor = VisitorImpl()
    total_sum = 0
    for item in elements:
        # Double dispatch: The element calls back to the visitor
        total_sum += item.accept(visitor)
    return total_sum

if __name__ == "__main__":
    elements = [
        Book(price=10, isbn_number="2324"),
        Book(price=22, isbn_number="222"),
        Fruit(price_per_kg=10, weight=1, name="apple"),
        Fruit(price_per_kg=10, weight=1, name="mango")
    ]

    total = calculate_total(elements)

    print(f"Total cost: {total}")