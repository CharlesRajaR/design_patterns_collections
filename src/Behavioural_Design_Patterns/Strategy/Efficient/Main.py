# The Strategy pattern enables selecting an algorithm at runtime.
# It defines a family of interchangeable algorithms (strategies) and encapsulates each one.
# The Context (PaymentProcessor) delegates the execution to the selected Strategy object.

from abc import ABC, abstractmethod

# --- Strategy Interface ---
class PaymentStrategy(ABC):
    """
    Declares an interface common to all supported algorithms (payment methods).
    """
    @abstractmethod
    def process_payment(self):
        pass

# --- Concrete Strategies ---
class Stripe(PaymentStrategy):
    """
    A concrete implementation of the payment algorithm.
    """
    def process_payment(self):
        print("payment processed by stripe")

class Razorpay(PaymentStrategy):
    """
    Another concrete implementation of the payment algorithm.
    """
    def process_payment(self):
        print("payment processed by razorpay")

class UPI(PaymentStrategy):
    """
    A third concrete implementation of the payment algorithm.
    """
    def process_payment(self):
        print("payment processed by upi")

# --- Context ---
class PaymentProcessor:
    """
    The Context uses a Strategy object and can change the strategy at runtime.
    """
    def __init__(self, payment_strategy: PaymentStrategy):
        self._payment_strategy = payment_strategy

    def process_payment(self):
        """
        Delegates the work to the chosen strategy object.
        """
        self._payment_strategy.process_payment()

    def set_payment_strategy(self, payment_strategy: PaymentStrategy):
        """
        Setter method to allow changing the strategy at runtime.
        """
        self._payment_strategy = payment_strategy

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Create the Concrete Strategy objects
    stripe_strategy = Stripe()
    razorpay_strategy = Razorpay()
    upi_strategy = UPI()

    # 2. Create the Context with an initial strategy
    payment_processor = PaymentProcessor(stripe_strategy)

    # Process payment using Stripe
    payment_processor.process_payment()

    # 3. Change the strategy at runtime
    payment_processor.set_payment_strategy(razorpay_strategy)
    # Process payment using Razorpay
    payment_processor.process_payment()

    # 4. Change the strategy again
    payment_processor.set_payment_strategy(upi_strategy)
    # Process payment using UPI
    payment_processor.process_payment()