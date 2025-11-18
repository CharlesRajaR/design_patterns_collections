from abc import ABC, abstractmethod

# Abstract Handler class
class Handler(ABC):
    """
    The Handler interface declares a method for building the chain of handlers.
    It also declares a method for executing a request.
    """
    def __init__(self):
        self._next_handler = None

    def set_next_handler(self, next_handler):
        """
        Sets the next handler in the chain.
        """
        self._next_handler = next_handler
        return next_handler # For convenient chaining

    @abstractmethod
    def handle(self, request):
        """
        Processes the request or passes it to the next handler.
        """
        if self._next_handler:
            return self._next_handler.handle(request)
        return None # Return None if no handler can process the request

# --- Concrete Handlers ---

class LowLevelHandler(Handler):
    def handle(self, request):
        if request.lower() == "low":
            print("Request processed by low level handler")
            return True
        else:
            # Pass to the next handler
            return super().handle(request)

class MidLevelHandler(Handler):
    def handle(self, request):
        if request.lower() == "mid":
            print("Request processed by mid level handler")
            return True
        else:
            # Pass to the next handler
            return super().handle(request)

class HighLevelHandler(Handler):
    def handle(self, request):
        if request.lower() == "high":
            print("Request processed by high level handler")
            return True
        else:
            # Pass to the next handler
            return super().handle(request)

# --- Main Client Code ---

if __name__ == "__main__":
    # Create the handlers
    low_level_handler = LowLevelHandler()
    mid_level_handler = MidLevelHandler()
    high_level_handler = HighLevelHandler()

    # Build the chain: low -> mid -> high
    low_level_handler.set_next_handler(mid_level_handler).set_next_handler(high_level_handler)

    # Requests to be handled
    requests = ["mid", "high", "low", "unknown"]

    print("--- Starting Requests ---")
    for req in requests:
        print(f"\nProcessing request: '{req}'")
        result = low_level_handler.handle(req)

        if not result:
            print("No handler to handle this request")
    print("--- Requests Complete ---")