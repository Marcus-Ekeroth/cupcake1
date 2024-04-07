package app.entities;

import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Haiku {

    public List<String> haikus = new ArrayList<>();

    public String pickRandomHaiku(){
        Random random = new Random();
        int randomNumber = random.nextInt(0, 6);
        String randomHaiku = getHaikuByIndex(randomNumber);
        return randomHaiku;
    }

    public void fillHaikuList(){
        haikus.add("Morning dew sparkles,\n" +
                "Whispering winds kiss the trees,\n" +
                "Nature's symphony.");
        haikus.add("Silent moonlit night,\n" +
                "Stars paint the sky with whispers,\n" +
                "Dreams dance in moonbeams.");
        haikus.add("Petals in the breeze,\n" +
                "Cherry blossoms softly fall,\n" +
                "Spring's gentle embrace.");
        haikus.add("Ocean's endless song,\n" +
                "Waves crashing on sandy shores,\n" +
                "Echoes of time's march.");
        haikus.add("Sunset paints the sky,\n" +
                "Colors blend in harmony,\n" +
                "Day bids night farewell.");
        haikus.add("Lonely mountain peak,\n" +
                "Yearning for the sky's embrace,\n" +
                "Whispers to the clouds.");
        haikus.add("Fireflies in the dark,\n" +
                "Twinkling lights in harmony,\n" +
                "Night's silent concert.");
    }

    public String getHaikuByIndex(int number){
        return haikus.get(number);
    }
}
