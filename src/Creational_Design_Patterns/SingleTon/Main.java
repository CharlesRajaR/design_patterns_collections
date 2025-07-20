package Creational_Design_Patterns.SingleTon;

public class Main {
    public static void main(String[] args) {
     run();
    }

    private static void run(){
        Logger logger = Logger.getInstance();
        logger.log("i am thread1");
        Logger logger1 = Logger.getInstance();
        logger1.log("i am thread2");
    }
}

class Logger{
    private static volatile Logger instance = null;

    private Logger(){}

    public static Logger getInstance(){
        if(instance == null){
            synchronized (Logger.class){
                instance = new Logger();
            }
        }
        return instance;
    }

    public void log(String message){
        System.out.println("log message is : "+message);
    }
}

