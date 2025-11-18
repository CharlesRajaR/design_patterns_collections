from abc import ABC, abstractmethod

# --- Abstract Base Class (Template) ---
class DataProcessor(ABC):
    """
    Defines the skeleton (template) of the algorithm in a method (process)
    and lets subclasses redefine certain steps.
    """

    # Template method - defines the skeleton of the algorithm
    def process(self):
        """
        The fixed, non-overridable sequence of steps.
        This acts as the template method.
        """
        self.read_data()
        self.process_data()
        self.save_data()  # Default implementation provided

    @abstractmethod
    def read_data(self):
        """
        Abstract operation - must be implemented by subclasses (the hook).
        """
        pass

    @abstractmethod
    def process_data(self):
        """
        Abstract operation - must be implemented by subclasses (the hook).
        """
        pass

    def save_data(self):
        """
        Concrete operation - common step with a default implementation.
        """
        print("save the data to disk")

# --- Concrete Subclasses ---
class ExcelDataProcessor(DataProcessor):
    """
    Redefines the abstract steps for processing Excel data.
    """
    def read_data(self):
        print("reading excel data")

    def process_data(self):
        print("Processing excel data")

class CsvDataProcessor(DataProcessor):
    """
    Redefines the abstract steps for processing CSV data.
    """
    def read_data(self):
        print("reading csv data")

    def process_data(self):
        print("process csv data")

# --- Client (Main Execution) ---
if __name__ == "__main__":
    print("--- Processing Excel Data ---")
    excel_data_processor = ExcelDataProcessor()
    # The 'process' method ensures the steps are run in the correct order
    excel_data_processor.process()

    print("\n--- Processing CSV Data ---")
    csv_data_processor = CsvDataProcessor()
    # The 'process' method ensures the steps are run in the correct order
    csv_data_processor.process()