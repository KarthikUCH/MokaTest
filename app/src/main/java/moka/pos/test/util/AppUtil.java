package moka.pos.test.util;

import java.util.Random;

/**
 * Created by karthikeyan on 25/1/18.
 */

public class AppUtil {

    public static int getRandomNumber(int min, int max) {

        Random rand = new Random();
        int number = rand.nextInt(max) + min;
        return number;
    }
}
