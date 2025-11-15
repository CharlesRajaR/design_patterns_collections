package Behavioural_Design_Patterns.ChainOfResponsibilityDesignPattern;

//               ==========================================
//               | CHAIN OF RESPONSIBILITY DESIGN PATTERN |
//               ==========================================
// When multiple objects handling the request without the sender knowing which object will actually process it

// Handler abstract class
abstract class Handler{
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract void handle(String request);
}

class LowLevelHandler extends Handler{

    @Override
    public void handle(String request) {
       if(request.equalsIgnoreCase("low")){
           System.out.println("request processes by low level handler");
       } else if (nextHandler != null) {
           nextHandler.handle(request);
       }else{
           System.out.println("no handler to handle this request");
       }
    }
}

class MidLevelHandler extends Handler{

    @Override
    public void handle(String request) {
        if(request.equalsIgnoreCase("mid")){
            System.out.println("request processes by mid level handler");
        } else if (nextHandler != null) {
            nextHandler.handle(request);
        }else{
            System.out.println("no handler to handle this request");
        }
    }
}

class HighLevelHandler extends Handler{

    @Override
    public void handle(String request) {
        if(request.equalsIgnoreCase("high")){
            System.out.println("request processes by high level handler");
        } else if (nextHandler != null) {
            nextHandler.handle(request);
        }else{
            System.out.println("no handler to handle this request");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Handler lowLevelHandler = new LowLevelHandler();
        Handler midLevelHandler = new MidLevelHandler();
        Handler highLevelHandler = new HighLevelHandler();

        lowLevelHandler.setNextHandler(midLevelHandler);
        midLevelHandler.setNextHandler(highLevelHandler);

        lowLevelHandler.handle("mid");
        lowLevelHandler.handle("high");
        lowLevelHandler.handle("low");
    }
}

