package softwaredesign;

public class CharacterFactory {
    public Character createCharacter(String charType){
        if(charType == "Buff"){
            System.out.println("creating BuffPirsoner");
            Character mychar = new BuffPrisoner();
            return mychar;
        }
        else if(charType == "Joker"){
            System.out.println("creating JokerPirsoner");
            Character mychar =  new JokerPrisoner();
            return mychar;
        }
        else if(charType == "Dancer"){
            System.out.println("creating DancerPirsoner");
            Character mychar =  new DancerPrisoner();
            return mychar;
        }
        else{
            System.out.println("creating nothing");
            return null;
        }
    }
}
