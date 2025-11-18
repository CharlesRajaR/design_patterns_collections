# The Builder pattern is used to construct a complex object step by step.
# It separates the construction of a complex object from its representation,
# allowing the same construction process to create different representations.

class Pizza:
    """
    The complex object being built. It should have a private constructor
    and be created only through the Builder.
    """
    def __init__(self, builder):
        # Fields are set using the data collected by the Builder
        self._base = builder.base
        self._cheese = builder.cheese
        self._toppings = builder.toppings

    def view_ingredients(self):
        """
        Displays the finalized state of the Pizza object.
        """
        print(f"Base: {self._base}")
        print(f"Cheese: {self._cheese}")
        print(f"Toppings: {self._toppings}")

    # Inner Builder Class
    class Builder:
        """
        The Builder class provides step-by-step methods for setting optional parameters
        and a final 'build' method to create the product.
        """
        def __init__(self):
            # Set default values for all ingredients
            self.base = "default base"
            self.cheese = "default cheese"
            self.toppings = "default toppings"

        def set_base(self, base: str):
            """Sets the base and returns the builder instance for chaining."""
            self.base = base
            return self

        def set_cheese(self, cheese: str):
            """Sets the cheese and returns the builder instance for chaining."""
            self.cheese = cheese
            return self

        def set_toppings(self, toppings: str):
            """Sets the toppings and returns the builder instance for chaining."""
            self.toppings = toppings
            return self

        def build(self) -> 'Pizza':
            """Constructs and returns the final Pizza object."""
            # The builder passes itself to the Pizza constructor
            return Pizza(self)

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Start with a new Builder instance
    pizza_builder = Pizza.Builder()

    # 2. Configure the Pizza using method chaining and call build()
    pizza = pizza_builder.set_base("bread").set_toppings("tomato").build()

    # 3. Use the final object
    pizza.view_ingredients()

    print("\n--- Another configuration example ---")

    # Example showing partial configuration and default usage
    custom_pizza = Pizza.Builder().set_cheese("Mozzarella").build()
    custom_pizza.view_ingredients()