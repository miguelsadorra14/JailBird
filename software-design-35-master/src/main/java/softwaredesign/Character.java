package softwaredesign;

import javafx.scene.image.Image;

public abstract class Character {
    public Image specialImage;
    public Image charImage;
    public Image charPushUp;
    public Image charPushDown;
    public String name;
    public Hunger hungerVital;
    public Sleepiness sleepVital;
    public Hygiene hygieneVital;
    public Mood moodVital;
    public Health healthVital;
    public boolean hungry;
    public boolean tired;
    public boolean dirty;
    public boolean sad;
    public boolean isAlive;

    public void feed() {
        hungerVital.value += 25;
        if(hungerVital.value > 100) hungerVital.value = 100;
    }
    public void clean(){
        hygieneVital.value += 30;
        if(hygieneVital.value > 100) hygieneVital.value = 100;
    }
    public void sleep(){
        sleepVital.value += 30;
        if(sleepVital.value > 100) sleepVital.value = 100;
    }
    public void increaseMood() {
        moodVital.value += 10;
        if(moodVital.value > 100) moodVital.value = 100;
    }
    public void updateHP(){// checks vitals and updates HP accordingly
        int toAdd = 0;
        if(hungry){
            toAdd -= hungerVital.criticalness;
            if(hungerVital.value == 0) toAdd -= hungerVital.criticalness;
        } else{ toAdd += 2;}
        if(tired){
            toAdd -= sleepVital.criticalness;
            if(sleepVital.value == 0) toAdd -= sleepVital.criticalness;
        } else{ toAdd += 2;}
        if(dirty){
            toAdd -= hygieneVital.criticalness;
            if(hygieneVital.value == 0) toAdd -= hygieneVital.criticalness;
        } else {toAdd += 2;}
        if(sad){
            toAdd -= moodVital.criticalness;
            if(moodVital.value == 0) toAdd -= moodVital.criticalness;
        } else { toAdd += 2;}
        healthVital.value += toAdd;
        if(healthVital.value > 100) healthVital.value = 100;
        if(healthVital.value <= 0){
            healthVital.value = 0;
            isAlive = false;
        }

        healthVital.notifyObservers();
        System.out.println("\nVitals Updated\n");
        System.out.print(" Hunger = " + hungerVital.value);
        System.out.print(" Sleep = " + sleepVital.value);
        System.out.print(" Hygiene = " + hygieneVital.value);
        System.out.print(" Mood = " + moodVital.value);
        System.out.print(" HP = " + healthVital.value);
    }
    public void updateVitals(){
        hungerVital.value -= 5;
        if(hungerVital.value < 20) {
            hungry = true;
            if(hungerVital.value < 0 ) hungerVital.value = 0;
        }
        else  hungry = false;

        hygieneVital.value -= 3;
        if(hygieneVital.value < 20) {
            dirty = true;
            if(hygieneVital.value < 0 ) hygieneVital.value = 0;
        }
        else  dirty = false;

        sleepVital.value -= 2;
        if(sleepVital.value < 20) {
            tired = true;
            if(sleepVital.value < 0 ) sleepVital.value = 0;
        }
        else  tired = false;

        moodVital.value -= 2;
        if(moodVital.value < 20) {
            sad = true;
            if(moodVital.value < 0 ) moodVital.value = 0;
        }
        else  sad = false;

        hungerVital.notifyObservers();
        hygieneVital.notifyObservers();
        sleepVital.notifyObservers();
        moodVital.notifyObservers();
    }
}
