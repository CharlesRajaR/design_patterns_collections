package Creational_Design_Patterns.AbstractFactory.Efficient;

import java.util.Scanner;

interface Button{
    void render();
}

class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("rendering windows button");
    }
}

class MacButton implements Button{
    @Override
    public void render() {
        System.out.println("rendering mac button");
    }
}

interface GuiFactory{
    Button createButton();
}

class MacFactory implements GuiFactory{

    @Override
    public Button createButton() {
        return new MacButton();
    }
}

class WindowsFactory implements GuiFactory{

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

//Client Code
class Application{
    private Button button;
     Application(GuiFactory factory){
         if(factory == null){
             System.out.println("Provide Valid factory");
             return;
         }
         this.button = factory.createButton();
     }

     public void render(){
         button.render();
     }

}
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your os name: ");
        String os = sc.nextLine();

        GuiFactory guiFactory = null;
        if(os.equalsIgnoreCase("Windows")){
            guiFactory = new WindowsFactory();
        }else if(os.equalsIgnoreCase("Mac")){
            guiFactory = new MacFactory();
        }else{
            System.out.println("os not available");
        }

        if(guiFactory == null){
            return;
        }
        Application app = new Application(guiFactory);
        app.render();
    }
}
