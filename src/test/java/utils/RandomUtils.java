package utils;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.String.format;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {

        return format("%s%s%s%s", getRandomInt(111,999), getRandomInt(111,999),
                getRandomInt(11,99), getRandomInt(11,99));

    }

}


