package Behavioural_Design_Patterns.VisitorDesignPattern;

//               ========================================
//               |        VISITOR DESIGN PATTERN        |
//               ========================================
//  It allow you to add new operations to the existing object without changing the structure of the class
//  It achieves this by separating the algorithm(visitor) from the object on which it operates.

//Visitor Interface
interface Visitor{
    int visit(Book book);
    int visit(Fruit fruit);
}

//Element Interface
interface ItemElement{
    int accept(Visitor visitor);
}

//Concrete elements
class Book implements ItemElement{
    private int price;
    private String isbnNumber;
    Book(int price, String isbnNumber){
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice(){
        return price;
    }

    public String getIsbnNumber(){
        return isbnNumber;
    }
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
class Fruit implements ItemElement{
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name){
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getWeight(){
        return weight;
    }

    public int getPricePerKg(){
        return pricePerKg;
    }

    public String getName(){
        return name;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

//Concrete visitor
class VisitorImpl implements Visitor{

    @Override
    public int visit(Book book) {
        //Algorithm logic goes here , whether discount giving or anything like that
        return book.getPrice();
    }

    @Override
    public int visit(Fruit fruit) {
        return fruit.getWeight() * fruit.getPricePerKg();
    }
}
public class Main {
    public static void main(String[] args) {
        ItemElement[] elements = new ItemElement[]{new Book(10, "2324"), new Book(22, "222"),
        new Fruit(10, 1, "apple"), new Fruit(10, 1, "mango")};

        int total = calculateTotal(elements);

        System.out.println("Total cost: "+total);
    }

    private static int calculateTotal(ItemElement[] elements){
        Visitor visitor = new VisitorImpl();

        int sum = 0;
        for (ItemElement item: elements){
            sum += item.accept(visitor);
        }

        return sum;
    }
}
