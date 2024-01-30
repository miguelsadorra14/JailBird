package softwaredesign;

import javafx.scene.image.Image;

public class DancerPrisoner extends Character {
    public DancerPrisoner(){
        specialImage = new Image(getClass().getResource("DancerDance1.png").toExternalForm());
        charImage = new Image(getClass().getResource("Dancer.png").toExternalForm());
        charPushDown = new Image(getClass().getResource("DancerDown.png").toExternalForm());
        charPushUp = new Image(getClass().getResource("DancerUp.png").toExternalForm());
        name = "Dancer";
        hungerVital = new Hunger();
        sleepVital = new Sleepiness();
        hygieneVital = new Hygiene();
        moodVital = new Mood();
        healthVital = new Health();
        hungry = false;
        tired = false;
        dirty = false;
        sad = false;
        isAlive = true;
    }


}
