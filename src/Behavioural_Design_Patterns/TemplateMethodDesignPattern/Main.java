package Behavioural_Design_Patterns.TemplateMethodDesignPattern;

//                    ======================================
//                    |   TEMPLATE METHOD DESIGN PATTERN   |
//                    ======================================
//    It defines the skeleton(template) of the algorithm in the base class but lets subclasses override specific step of the algorithm
//    It allows subclasses to redefine specific part of the algorithm while keeping the overall flow unchanged.


abstract class DataProcessor{
    //Template method - defines the skeleton of the algorithm
    public final void process(){
        readData();
        processData();
        saveData();
    }

    public abstract void readData();
    public abstract void processData();
    void saveData(){
        System.out.println("save the data to disk");
    }

}

class ExcelDataProcessor extends DataProcessor{

    @Override
    public void readData() {
        System.out.println("reading excel data");
    }

    @Override
    public void processData() {
        System.out.println("Processing excel data");
    }
}

class CsvDataProcessor extends DataProcessor{

    @Override
    public void readData() {
        System.out.println("reading csv data");
    }

    @Override
    public void processData() {
        System.out.println("process csv data");
    }
}
public class Main {
    public static void main(String[] args) {
        ExcelDataProcessor excelDataProcessor = new ExcelDataProcessor();
        excelDataProcessor.process();

        CsvDataProcessor csvDataProcessor = new CsvDataProcessor();
        csvDataProcessor.process();
    }
}
