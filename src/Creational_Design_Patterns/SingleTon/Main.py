import threading
import time

class ThreadSafeSingleton:
    """
    Ensures that only one instance of the class is created, even in a multi-threaded
    environment, using the double-checked locking principle.
    """
    _instance = None
    _lock = threading.Lock() # Class-level lock

    def __new__(cls, *args, **kwargs):
        # 1. First check (optimization: outside the lock)
        if cls._instance is not None:
            return cls._instance

        # 2. Acquire lock for thread safety
        with cls._lock:
            # 3. Second check (double-checked locking principle)
            if cls._instance is None:
                # Simulate a delay to emphasize threading issues if the lock wasn't there
                # time.sleep(0.001)

                cls._instance = super().__new__(cls)

        # 4. Return the instance
        return cls._instance

    def __init__(self, initial_value=None):
        """
        Guards against re-initialization, ensuring setup runs only once.
        """
        # Check if the instance has been initialized before
        if not hasattr(self, '_is_initialized'):
            self.value = initial_value
            print(f"[{threading.current_thread().name}] Singleton initialized with value: {self.value}")
            self._is_initialized = True
        else:
            # Optional: Show that __init__ is called but ignored on subsequent calls
            # print(f"[{threading.current_thread().name}] Instance already exists and is not re-initialized.")
            pass

    def get_value(self):
        return self.value

# --- Client Code ---
def create_singleton_instance(value):
    """Function run by multiple threads to create the Singleton."""
    instance = ThreadSafeSingleton(value)

    # Check the state of the shared instance
    print(f"[{threading.current_thread().name}] Retrieved instance. Value: {instance.get_value()}")

if __name__ == "__main__":
    print("--- Testing Thread-Safe Singleton ---")

    # Create a list of thread objects
    threads = []

    # Create 10 threads, each attempting to initialize the Singleton with a different value
    for i in range(10):
        thread = threading.Thread(
            target=create_singleton_instance,
            args=(f"Config-{i+1}",),
            name=f"Thread-{i+1}"
        )
        threads.append(thread)

    # Start all threads
    for thread in threads:
        thread.start()

    # Wait for all threads to complete
    for thread in threads:
        thread.join()

    print("\n--- Final Verification ---")

    # All threads should have created/retrieved the same instance
    final_instance = ThreadSafeSingleton()
    print(f"Final retrieved instance value: {final_instance.get_value()}")

    # Ensure all threads retrieved the very first initialized instance (Config-1)
    # The output will confirm that only one thread printed the "Singleton initialized" message.