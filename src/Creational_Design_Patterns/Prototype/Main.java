package Creational_Design_Patterns.Prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
       CharacterFactory factory = new CharacterFactory();
       Character character1 = factory.createCharacterWithAge(1);
       System.out.println(character1.toString());
       Character character2 = factory.createCharacterWithName("rcr");
       System.out.println(character2.toString());
       Character character3 = factory.createCharacterWithNation("tamil nadu");
       System.out.println(character3.toString());

    }
}

class Character implements Cloneable{
    private String name;
    private int age;
    private String nationality;

    Character(String name, int age, String nationality){
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public Character clone()throws CloneNotSupportedException{
        return (Character) super.clone();
    }

    public String toString(){
        return "Character Name: "+name+"\nCharacter age: "+age+"\nCharacter" +
                " nationality: "+nationality+"\n";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }
}


class CharacterFactory{
    Character prototypeCharacter;
    CharacterFactory(){
        prototypeCharacter = new Character("name", 22, "indian");
    }

    public Character createCharacterWithName(String name) throws CloneNotSupportedException {
        Character character = prototypeCharacter.clone();
        return new Character(name, character.getAge(), character.getNationality());
    }

    public Character createCharacterWithAge(int age) throws CloneNotSupportedException {
        Character character = prototypeCharacter.clone();
        return new Character(character.getName(), age, character.getNationality());
    }

    public Character createCharacterWithNation(String nation) throws CloneNotSupportedException {
        Character character = prototypeCharacter.clone();
        return new Character(character.getName(), character.getAge(), nation);
    }
}