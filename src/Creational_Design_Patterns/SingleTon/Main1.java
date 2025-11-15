package Creational_Design_Patterns.SingleTon;

public class Main1 {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.createInstance();

        System.out.println(singleTon.message);
    }
}

class SingleTon{
    String message = "Hello from SingleTon class";
    private SingleTon(){}

    private static volatile SingleTon instance;

    public static SingleTon createInstance(){
        if(instance != null){
            return instance;
        }

        synchronized (SingleTon.class){
            if(instance != null){
                return instance;
            }

            instance = new SingleTon();
            return instance;
        }
    }
}
