package softwaredesign;

import javafx.scene.image.Image;

public class BuffPrisoner extends Character {
    public  BuffPrisoner(){
        specialImage = new Image(getClass().getResource("BuffFlex.png").toExternalForm()); // should make a flexing image;
        charImage =  new Image(getClass().getResource("Buff.png").toExternalForm());
        charPushDown = new Image(getClass().getResource("BuffDown.png").toExternalForm());
        charPushUp =  new Image(getClass().getResource("BuffUp.png").toExternalForm());
        name = "Buff";
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
