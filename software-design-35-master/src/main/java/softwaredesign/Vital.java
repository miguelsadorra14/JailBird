package softwaredesign;

import javafx.application.Application;
import javafx.beans.Observable;

import java.util.ArrayList;

public abstract class Vital implements Subject {
     private Observer myTracker = null;
     public int value = 100;
     String text;
     @Override
     public void attach(Observer obs) {
        myTracker = obs;
     }
     @Override
     public void detach() {
        myTracker = null;
     }
     @Override
     public void notifyObservers() {
        if(myTracker != null){
            myTracker.update(value);
        }
        else{
            System.out.println("NO OBSERVER");
        }
     }
}

class Health extends Vital{
     String text = "HP";
}
class Hunger extends Vital{
     String text = "Hunger";
     public int criticalness = 5;
}
class Mood extends Vital{
     String text = "Mood";
     public int criticalness = 3;
}
class Hygiene extends Vital{
     String text = "Hygiene";
     public int criticalness = 2;
}
class Sleepiness extends Vital{
     String text = "Sleepiness";
    public int criticalness = 4;
}
