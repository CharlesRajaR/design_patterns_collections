package Structural_Design_Patterns.CompositeDesignPattern;

//                         ==================================
//                         |    COMPOSITE DESIGN PATTERN    |
//                         ==================================
//      It lets you treat individual object and composition of the objects uniformly
//      In simple terms : you can perform operations on a single object (leaf) or a group of object the same way

import java.util.ArrayList;
import java.util.List;

//Component
interface FileSystemComponent{
    void showDetails();
}

//Leaf
class File implements FileSystemComponent{
    private String name;
    public File(String name){
        this.name = name;
    }
    @Override
    public void showDetails() {
        System.out.println("File: "+name);
    }
}

class Folder implements FileSystemComponent{

    private String name;
    private List<FileSystemComponent> filesAndFolders = new ArrayList<>();

    public Folder(String name){
        this.name = name;
    }

    public void addFileComponent(FileSystemComponent fileOrFolder){
        filesAndFolders.add(fileOrFolder);
    }

    public void removeFileComponent(FileSystemComponent fileOrFolder){
        filesAndFolders.remove(fileOrFolder);
    }
    @Override
    public void showDetails() {
        System.out.println("Folder: "+name);
        for(FileSystemComponent fileSystemComponent: filesAndFolders){
            fileSystemComponent.showDetails();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        File file1 = new File("file1");
        File file2 = new File("file2");
        File file3 = new File("file3");

        Folder folder = new Folder("folder1");
        folder.addFileComponent(file2);
        folder.addFileComponent(file3);

        Folder root = new Folder("root");
        root.addFileComponent(file1);
        root.addFileComponent(folder);

        root.showDetails();
    }
}
