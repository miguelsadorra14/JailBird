package softwaredesign;

import javafx.scene.image.Image;

public class JokerPrisoner extends Character {
    public JokerPrisoner(){
        specialImage = new Image(getClass().getResource("JokerLaugh.png").toExternalForm());
        charImage = new Image(getClass().getResource("Joker.png").toExternalForm());
        charPushDown = new Image(getClass().getResource("JokerDown.png").toExternalForm());
        charPushUp = new Image(getClass().getResource("JokerUp.png").toExternalForm());
        name = "Joker";
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

