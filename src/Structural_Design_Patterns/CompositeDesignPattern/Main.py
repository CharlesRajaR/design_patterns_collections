from abc import ABC, abstractmethod

# --- Component Interface ---
class FileSystemComponent(ABC):
    """
    The Component interface for both simple (Leaf) and complex (Composite) objects.
    """
    @abstractmethod
    def show_details(self):
        pass

# --- Leaf ---
class File(FileSystemComponent):
    """
    The Leaf represents the simple objects in the composition. It cannot have children.
    """
    def __init__(self, name: str):
        self._name = name

    def show_details(self):
        print(f"File: {self._name}")

# --- Composite ---
class Folder(FileSystemComponent):
    """
    The Composite represents components that can have children.
    It stores and manages child components and implements component methods by delegating to its children.
    """
    def __init__(self, name: str):
        self._name = name
        self._files_and_folders = [] # List to hold child components

    def add_file_component(self, file_or_folder: FileSystemComponent):
        self._files_and_folders.append(file_or_folder)

    def remove_file_component(self, file_or_folder: FileSystemComponent):
        self._files_and_folders.remove(file_or_folder)

    def show_details(self):
        # The core of the Composite pattern: Recursively delegates the operation to children
        print(f"Folder: {self._name}")
        for file_system_component in self._files_and_folders:
            file_system_component.show_details()

# --- Client (Main Execution) ---
if __name__ == "__main__":
    # 1. Create Leaf objects (Files)
    file1 = File("file1")
    file2 = File("file2")
    file3 = File("file3")

    # 2. Create a Composite object (Folder) and add leaves
    folder = Folder("folder1")
    folder.add_file_component(file2)
    folder.add_file_component(file3)

    # 3. Create the Root Composite object and add both a Leaf and another Composite
    root = Folder("root")
    root.add_file_component(file1)    # Adding a file (Leaf)
    root.add_file_component(folder)   # Adding a folder (Composite)

    # Calling show_details() on the root component triggers the recursive display
    print("--- Displaying Root Structure ---")
    root.show_details()